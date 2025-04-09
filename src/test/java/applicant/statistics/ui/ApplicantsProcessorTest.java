package applicant.statistics.ui;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantsProcessorTest {

    @Test
    void shouldProcessValidCsvCorrectly() {
        ApplicantsProcessor processor = new ApplicantsProcessor();
        InputStream csv = getClass().getClassLoader().getResourceAsStream("applicants_valid.csv");
        assertNotNull(csv);

        String json = processor.processApplicants(csv);

        assertTrue(json.contains("\"uniqueApplicants\" : 3"));
        assertTrue(json.contains("\"topApplicants\""));
        assertTrue(json.contains("Popescu"));
        assertTrue(json.contains("Pop"));
        assertTrue(json.contains("Ionescu"));
        assertTrue(json.contains("\"averageScore\" : 8.75"));

        assertEquals(3, processor.getTotalLines());
        assertEquals(3, processor.getValidLines());
        assertEquals(0, processor.getSkippedLines());
    }

    @Test
    void shouldSkipInvalidRows() {
        ApplicantsProcessor processor = new ApplicantsProcessor();
        InputStream csv = getClass().getClassLoader().getResourceAsStream("applicants_invalid.csv");
        assertNotNull(csv);

        String json = processor.processApplicants(csv);

        assertTrue(json.contains("\"uniqueApplicants\" : 1"));
        assertTrue(json.contains("\"topApplicants\""));
        assertTrue(json.contains("Person"));
        assertTrue(json.contains("\"averageScore\" : 10.00"));

        assertEquals(4, processor.getTotalLines());
        assertEquals(1, processor.getValidLines());
        assertEquals(3, processor.getSkippedLines());
    }
}
