package applicant.statistics.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeValidatorTest {

    private final DateTimeValidator validator = new DateTimeValidator();

    @Test
    void shouldAcceptValidDateTime() {
        assertDoesNotThrow(() -> validator.validate("2025-04-09T12:30:45"));
    }

    @Test
    void shouldRejectInvalidDateTime() {
        assertThrows(ApplicantException.class, () -> validator.validate("invalid-date"));
        assertThrows(ApplicantException.class, () -> validator.validate("2025-13-40T99:99:99"));
    }
}
