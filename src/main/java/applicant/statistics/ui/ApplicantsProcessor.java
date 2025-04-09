package applicant.statistics.ui;

import applicant.statistics.repository.ApplicantCsvRepository;
import applicant.statistics.repository.ApplicantRepository;
import applicant.statistics.service.impl.ApplicantProcessorResult;
import applicant.statistics.service.impl.ApplicantServiceImpl;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * UI layer that transforms applicant data from CSV input stream to a formatted JSON output.
 *
 * Responsibilities:
 * - Coordinates the reading and processing of applicant data from the input CSV stream.
 * - Uses the ApplicantServiceImpl to handle the logic of validation, score adjustments, and top applicant extraction.
 * - Serializes the processed data (top applicants and their scores) into a well-structured JSON format.
 * - Provides detailed statistics on the processing, such as the total number of rows processed, valid rows, and skipped rows.
 */
public class ApplicantsProcessor {

    private static final Logger logger = LogManager.getLogger(ApplicantsProcessor.class);

    private int totalLines = 0;
    private int validLines = 0;
    private int skippedLines = 0;

    public int getTotalLines() { return totalLines; }
    public int getValidLines() { return validLines; }
    public int getSkippedLines() { return skippedLines; }

    public String processApplicants(InputStream csvStream) {
        ApplicantRepository repository = new ApplicantCsvRepository();
        ApplicantServiceImpl service = new ApplicantServiceImpl(repository);

        service.resetCounters();
        ApplicantProcessorResult result = service.processApplicants(csvStream);

        this.totalLines = service.getTotalRows();
        this.validLines = service.getValidRows();
        this.skippedLines = service.getSkippedRows();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
            printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            return mapper.writer(printer).writeValueAsString(result) + System.lineSeparator();
        } catch (IOException e) {
            logger.error("Failed to serialize output to JSON", e);
            return "{}";
        }
    }
}