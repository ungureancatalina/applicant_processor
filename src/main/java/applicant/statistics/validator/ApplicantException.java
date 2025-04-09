package applicant.statistics.validator;

/**
 * Custom exception thrown when validation of an applicant field fails.
 */
public class ApplicantException extends Exception {
    public ApplicantException(String message) {
        super(message);
    }

    public static ApplicantException invalidName(String input) {
        return new ApplicantException("Invalid name: " + input);
    }

    public static ApplicantException invalidEmail(String input) {
        return new ApplicantException("Invalid email: " + input);
    }

    public static ApplicantException invalidScore(String input) {
        return new ApplicantException("Invalid score: " + input);
    }

    public static ApplicantException invalidDate(String input) {
        return new ApplicantException("Invalid datetime: " + input);
    }
}
