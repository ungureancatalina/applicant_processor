package applicant.statistics.service;

import applicant.statistics.service.impl.ApplicantProcessorResult;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantProcessorResultTest {

    @Test
    void shouldReturnValuesFromConstructorCorrectly() {
        ApplicantProcessorResult result = new ApplicantProcessorResult(
                1,
                List.of("Popescu", "Ionescu", "Marin"),
                BigDecimal.valueOf(8.77)
        );

        assertEquals(1, result.getUniqueApplicants());
        assertEquals(List.of("Popescu", "Ionescu", "Marin"), result.getTopApplicants());
        assertEquals(BigDecimal.valueOf(8.77), result.getAverageScore());
    }
}
