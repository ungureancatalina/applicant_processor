package applicant.statistics.service.impl;

import applicant.statistics.domain.Applicant;
import applicant.statistics.repository.ApplicantRepository;
import applicant.statistics.service.ApplicantService;
import applicant.statistics.service.util.ScoreAdjuster;
import applicant.statistics.service.util.ScoreAverager;
import applicant.statistics.service.util.TopApplicantsCalculator;
import applicant.statistics.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Coordinates the full processing pipeline for applicant data.
 *
 * Responsibilities:
 * - Retrieves raw CSV lines from the repository
 * - Validates each row using validators (name, email, date, score)
 * - Ignores invalid rows
 * - Builds a map of unique applicants, keeping the latest entry per email
 * - Applies score adjustments based on delivery timing (bonus/malus)
 * - Extracts top 3 applicants by final score
 * - Calculates the average score of the top half (before adjustments)
 */

public class ApplicantServiceImpl implements ApplicantService {

    private static final Logger logger = LogManager.getLogger(ApplicantServiceImpl.class);

    private final ApplicantRepository repository;
    private final List<Validation> validators;
    private int totalRows = 0;
    private int validRows = 0;
    private int skippedRows = 0;

    public ApplicantServiceImpl(ApplicantRepository repository) {
        this.repository = repository;
        this.validators = List.of(
                new NameValidator(),
                new EmailValidator(),
                new DateTimeValidator(),
                new ScoreValidator()
        );
    }

    @Override
    public ApplicantProcessorResult processApplicants(InputStream csvStream) {
        Map<String, Applicant> uniqueApplicants = new HashMap<>();
        List<String[]> rawRows = repository.readRawApplicantLines(csvStream);
        this.totalRows = rawRows.size();
        this.validRows = 0;

        for (String[] row : rawRows) {
            try {
                validateRow(row);
                Applicant applicant = parseToApplicant(row);
                uniqueApplicants.put(applicant.getEmail(), applicant);
                validRows++;
            } catch (ApplicantException e) {
                logger.warn("Validation failed for row: {} — Reason: {}", Arrays.toString(row), e.getMessage());
                skippedRows++;
            }

        }

        if (uniqueApplicants.isEmpty()) {
            logger.warn("No valid applicants found after processing.");
            return new ApplicantProcessorResult(0, List.of(), BigDecimal.ZERO);
        }

        ScoreAdjuster.apply(uniqueApplicants.values());
        List<String> topLastNames = TopApplicantsCalculator.extractTop3LastNames(uniqueApplicants.values());
        BigDecimal averageScore = ScoreAverager.calculateTopHalfAverage(uniqueApplicants.values());

        return new ApplicantProcessorResult(uniqueApplicants.size(), topLastNames, averageScore);
    }

    private void validateRow(String[] row) throws ApplicantException {
        new NameValidator().validate(row[0].trim());
        new EmailValidator().validate(row[1].trim());
        new DateTimeValidator().validate(row[2].trim());
        new ScoreValidator().validate(row[3].trim());
    }

    private Applicant parseToApplicant(String[] row) {
        String name = row[0].trim();
        String email = row[1].trim();
        LocalDateTime dateTime = LocalDateTime.parse(row[2].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        double score = Double.parseDouble(row[3].trim());
        return new Applicant(name, email, dateTime, score);
    }


    public int getTotalRows() {
        return totalRows;
    }

    public int getValidRows() {
        return validRows;
    }

    public int getSkippedRows() {
        return skippedRows;
    }

    public void resetCounters() {
        totalRows = 0;
        validRows = 0;
        skippedRows = 0;
    }
}