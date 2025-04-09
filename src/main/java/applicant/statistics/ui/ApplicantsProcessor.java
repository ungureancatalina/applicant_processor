package applicant.statistics.ui;

import applicant.statistics.repository.ApplicantCsvRepository;
import applicant.statistics.repository.ApplicantRepository;
import applicant.statistics.service.ApplicantService;
import applicant.statistics.service.impl.ApplicantProcessorResult;
import applicant.statistics.service.impl.ApplicantServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Main logic holder for transforming applicant input stream into formatted JSON output.
 * This class is called by an external CLI entry point.
 */
public class ApplicantsProcessor {

    private static final Logger logger = LogManager.getLogger(ApplicantsProcessor.class);

    private int totalLines = 0;
    private int validLines = 0;
    private int skippedLines = 0;

    public int getTotalLines() { return totalLines; }
    public int getValidLines() { return validLines; }
    public int getSkippedLines() { return skippedLines; }

    /**
     * Processes the given CSV input stream and returns a JSON string with the result.
     * Includes summary log output for tracking line statistics.
     *
     * @param csvStream the input stream of CSV data
     * @return JSON-formatted string containing applicant statistics
     */
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
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            logger.error("Failed to serialize output to JSON", e);
            return "{}";
        }
    }
}