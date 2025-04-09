package applicant.statistics.validator;

/**
 * General interface for validating fields of an applicant.
 * Each implementation should handle the validation of one specific input field.
 */
public interface Validation {
    void validate(String value) throws ApplicantException;
}
