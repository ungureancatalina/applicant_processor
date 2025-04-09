package applicant.statistics.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    private final NameValidator validator = new NameValidator();

    @Test
    void shouldRejectNullOrEmptyName() {
        assertThrows(ApplicantException.class, () -> validator.validate(""));
        assertThrows(ApplicantException.class, () -> validator.validate("   "));
    }

    @Test
    void shouldRejectNameWithOnlyOneWord() {
        assertThrows(ApplicantException.class, () -> validator.validate("Ana"));
    }

    @Test
    void shouldAcceptValidFullName() {
        assertDoesNotThrow(() -> validator.validate("Ana Popescu"));
        assertDoesNotThrow(() -> validator.validate("Ana Maria Popescu"));
    }
}
