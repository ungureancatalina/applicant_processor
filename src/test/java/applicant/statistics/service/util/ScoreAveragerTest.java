package applicant.statistics.service.util;

import applicant.statistics.domain.Applicant;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreAveragerTest {

    @Test
    void shouldComputeTopHalfAverageCorrectly() {
        List<Applicant> applicants = List.of(
                new Applicant("One", "1@mail.com", LocalDateTime.now(), 9.0),
                new Applicant("Two", "2@mail.com", LocalDateTime.now(), 6.0),
                new Applicant("Three", "3@mail.com", LocalDateTime.now(), 8.0)
        );

        BigDecimal result = ScoreAverager.calculateTopHalfAverage(applicants);
        assertEquals(BigDecimal.valueOf(8.5).setScale(2), result);
    }
}
