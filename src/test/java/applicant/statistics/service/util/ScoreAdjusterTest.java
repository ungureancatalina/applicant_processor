package applicant.statistics.service.util;

import applicant.statistics.domain.Applicant;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreAdjusterTest {

    @Test
    void shouldApplyBonusAndMalus() {
        List<Applicant> applicants = List.of(
                new Applicant("First", "a@mail.com", LocalDateTime.parse("2025-04-07T10:00:00"), 6.0), // bonus
                new Applicant("Second", "b@mail.com", LocalDateTime.parse("2025-04-09T12:30:00"), 8.0), // malus
                new Applicant("Third", "c@mail.com", LocalDateTime.parse("2025-04-09T10:00:00"), 7.5)   // unchanged
        );

        ScoreAdjuster.apply(applicants);

        assertEquals(7.0, applicants.get(0).getAdjustedScore());
        assertEquals(7.0, applicants.get(1).getAdjustedScore());
        assertEquals(7.5, applicants.get(2).getAdjustedScore());
    }
}
