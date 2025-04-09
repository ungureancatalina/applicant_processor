package applicant.statistics.repository;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantCsvRepositoryTest {

    @Test
    void shouldReadLinesIgnoringHeader() {
        String csv = "name,email,delivery_datetime,score\n" +
                "Ana Pop,ana@example.com,2025-04-07T10:00:00,8.0\n" +
                "John Doe,john@example.com,2025-04-07T12:00:00,9.0";
        InputStream stream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));

        ApplicantRepository repo = new ApplicantCsvRepository();
        List<String[]> result = repo.readRawApplicantLines(stream);

        assertEquals(2, result.size());
        assertArrayEquals(new String[]{"Ana Pop", "ana@example.com", "2025-04-07T10:00:00", "8.0"}, result.get(0));
    }

    @Test
    void shouldSkipMalformedLine() {
        String csv = "name,email,delivery_datetime,score\n" +
                "InvalidLineWithTooFewColumns";
        InputStream stream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));
        ApplicantRepository repo = new ApplicantCsvRepository();

        List<String[]> result = repo.readRawApplicantLines(stream);
        assertEquals(0, result.size());
    }

    @Test
    void shouldThrowOnNullInputStream() {
        ApplicantRepository repo = new ApplicantCsvRepository();
        assertThrows(RepositoryException.class, () -> repo.readRawApplicantLines(null));
    }

    @Test
    void shouldSkipLineWithEmptyField() {
        String csv = "name,email,delivery_datetime,score\n" +
                "Ana Pop,,2025-04-07T10:00:00,8.0";
        InputStream stream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));

        ApplicantRepository repo = new ApplicantCsvRepository();
        List<String[]> result = repo.readRawApplicantLines(stream);
        assertEquals(0, result.size());
    }

}
