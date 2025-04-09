package applicant.statistics.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreValidatorTest {

    private final ScoreValidator validator = new ScoreValidator();

    @Test
    void shouldAcceptScoreWithinBounds() {
        assertDoesNotThrow(() -> validator.validate("9.99"));
        assertDoesNotThrow(() -> validator.validate("0.0"));
        assertDoesNotThrow(() -> validator.validate("10"));
    }

    @Test
    void shouldRejectNegativeScore() {
        assertThrows(ApplicantException.class, () -> validator.validate("-1"));
    }

    @Test
    void shouldRejectAboveMaxScore() {
        assertThrows(ApplicantException.class, () -> validator.validate("10.1"));
    }

    @Test
    void shouldRejectNonNumericScore() {
        assertThrows(ApplicantException.class, () -> validator.validate("abc"));
    }
}
