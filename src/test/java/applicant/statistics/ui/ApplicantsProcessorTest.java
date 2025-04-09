package applicant.statistics.ui;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantsProcessorTest {

    @Test
    void shouldProcessValidCsvCorrectly() {
        ApplicantsProcessor processor = new ApplicantsProcessor();
        InputStream csv = getClass().getClassLoader().getResourceAsStream("csv/applicants_valid.csv");
        assertNotNull(csv);

        String json = processor.processApplicants(csv);
        assertTrue(json.contains("\"uniqueApplicants\" : 3"));
        assertTrue(json.contains("\"averageScore\""));

        assertEquals(3, processor.getTotalLines());
        assertEquals(3, processor.getValidLines());
        assertEquals(0, processor.getSkippedLines());
    }

    @Test
    void shouldSkipInvalidRows() {
        ApplicantsProcessor processor = new ApplicantsProcessor();
        InputStream csv = getClass().getClassLoader().getResourceAsStream("csv/applicants_invalid.csv");
        assertNotNull(csv);

        String json = processor.processApplicants(csv);
        assertTrue(json.contains("\"uniqueApplicants\" : 1"));
        assertTrue(json.contains("\"averageScore\" : 10.00"));

        assertTrue(processor.getSkippedLines() > 0);
        assertEquals(processor.getTotalLines(), processor.getValidLines() + processor.getSkippedLines());
    }
}
