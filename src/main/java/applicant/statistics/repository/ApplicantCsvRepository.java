package applicant.statistics.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads raw CSV data line by line without applying any validation or parsing logic.
 * Data will be validated and transformed inside the service layer.
 *
 * Logs:
 * - warn for skipped malformed lines
 * - error for IO issues during read
 * - error for unsupported content type or format detection failures
 */
public class ApplicantCsvRepository implements ApplicantRepository {
    private static final int EXPECTED_COLUMNS = 4;
    private static final Logger logger = LogManager.getLogger(ApplicantCsvRepository.class);

    @Override
    public List<String[]> readRawApplicantLines(InputStream csvStream) {
        if (csvStream == null) {
            logger.error("Input stream is null. Cannot read applicants.");
            throw RepositoryException.nullInputStream();
        }

        List<String[]> rawEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    logger.warn("Line {} is empty and has been skipped.", lineNumber);
                    continue;
                }

                if (isFirstLine && line.toLowerCase().contains("email")) {
                    isFirstLine = false;
                    continue; // Skip header
                }

                if (line.chars().filter(ch -> ch == ',').count() < EXPECTED_COLUMNS - 1) {
                    logger.warn("Line {} skipped: missing delimiters — content: {}", lineNumber, line);
                    continue;
                }

                String[] tokens = line.split(",");
                if (tokens.length != EXPECTED_COLUMNS) {
                    logger.warn("Line {} skipped: expected {} columns, found {} — content: {}", lineNumber, EXPECTED_COLUMNS, tokens.length, line);
                    continue;
                }

                boolean hasEmptyField = false;
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].trim().isEmpty()) {
                        logger.warn("Line {} has empty field at column index {}: {}", lineNumber, i, line);
                        hasEmptyField = true;
                        break;
                    }
                }

                if (hasEmptyField) continue;

                rawEntries.add(tokens);
            }

        } catch (IOException e) {
            logger.error("I/O error occurred while reading applicants.csv input stream", e);
            throw RepositoryException.failedToReadSource(e);
        } catch (RuntimeException e) {
            logger.error("Unexpected runtime error at repository layer", e);
            throw RepositoryException.unexpectedData(e.getMessage());
        }

        return rawEntries;
    }
}