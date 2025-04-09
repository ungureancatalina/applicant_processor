package applicant.statistics.service.util;

import applicant.statistics.domain.Applicant;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopApplicantsCalculatorTest {

    @Test
    void shouldReturnTop3SortedByAdjustedScore() {
        Applicant a1 = new Applicant("A Last", "a@mail.com", LocalDateTime.now(), 8.0);
        Applicant a2 = new Applicant("B Last", "b@mail.com", LocalDateTime.now(), 9.0);
        Applicant a3 = new Applicant("C Last", "c@mail.com", LocalDateTime.now(), 7.0);
        Applicant a4 = new Applicant("D Last", "d@mail.com", LocalDateTime.now(), 10.0);

        a1.setAdjustedScore(8.0);
        a2.setAdjustedScore(9.0);
        a3.setAdjustedScore(7.0);
        a4.setAdjustedScore(10.0);

        List<String> top = TopApplicantsCalculator.extractTop3LastNames(List.of(a1, a2, a3, a4));
        assertEquals(List.of("Last", "Last", "Last"), top);
    }
}
