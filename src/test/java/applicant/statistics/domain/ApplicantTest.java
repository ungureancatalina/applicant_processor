package applicant.statistics.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantTest {

    @Test
    void shouldExtractLastNameCorrectly() {
        Applicant applicant = new Applicant("Ana Maria Popescu", "ana@example.com",
                LocalDateTime.parse("2025-04-07T10:00:00"), 8.0);
        assertEquals("Popescu", applicant.getLastName());
    }

    @Test
    void adjustedScoreShouldBeInitiallyRawScore() {
        Applicant applicant = new Applicant("John Smith", "john@example.com",
                LocalDateTime.parse("2025-04-07T10:00:00"), 7.0);
        assertEquals(7.0, applicant.getAdjustedScore());
    }

    @Test
    void adjustedScoreShouldBeSettable() {
        Applicant applicant = new Applicant("John Smith", "john@example.com",
                LocalDateTime.parse("2025-04-07T10:00:00"), 7.0);
        applicant.setAdjustedScore(9.0);
        assertEquals(9.0, applicant.getAdjustedScore());
    }

    @Test
    void equalsShouldOnlyConsiderEmail() {
        Applicant a1 = new Applicant("John Smith", "john@example.com",
                LocalDateTime.parse("2025-04-07T10:00:00"), 8.0);
        Applicant a2 = new Applicant("Johnny Smith", "john@example.com",
                LocalDateTime.parse("2025-04-08T12:00:00"), 9.0);
        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    void toStringShouldContainDetails() {
        Applicant applicant = new Applicant("Jane Doe", "jane@example.com",
                LocalDateTime.parse("2025-04-07T11:00:00"), 9.5);
        String output = applicant.toString();
        assertTrue(output.contains("Jane Doe"));
        assertTrue(output.contains("jane@example.com"));
        assertTrue(output.contains("score=9.5"));
    }

    @Test
    void shouldWorkCorrectlyInHashSet() {
        Set<Applicant> set = new HashSet<>();
        set.add(new Applicant("A", "same@email.com", LocalDateTime.now(), 5.0));
        set.add(new Applicant("B", "same@email.com", LocalDateTime.now(), 6.0));
        assertEquals(1, set.size());
    }
}
