package applicant.statistics.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private final EmailValidator validator = new EmailValidator();

    @Test
    void shouldRejectEmptyOrNullEmail() {
        assertThrows(ApplicantException.class, () -> validator.validate(""));
        assertThrows(ApplicantException.class, () -> validator.validate(null));
    }

    @Test
    void shouldRejectNonAsciiCharacters() {
        assertThrows(ApplicantException.class, () -> validator.validate("ăîșț@example.com"));
    }

    @Test
    void shouldRejectEmailWithoutAtOrDot() {
        assertThrows(ApplicantException.class, () -> validator.validate("invalid.email.com"));
        assertThrows(ApplicantException.class, () -> validator.validate("invalid@emailcom"));
    }

    @Test
    void shouldRejectIfStartsOrEndsIncorrectly() {
        assertThrows(ApplicantException.class, () -> validator.validate("1user@example.com"));
        assertThrows(ApplicantException.class, () -> validator.validate("user@example.com1"));
    }

    @Test
    void shouldAcceptValidEmail() {
        assertDoesNotThrow(() -> validator.validate("john.doe@example.com"));
    }
}
