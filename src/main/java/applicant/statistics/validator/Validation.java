package applicant.statistics.validator;

/**
 * General interface for validating fields of an applicant.
 */
public interface Validation {
    void validate(String value) throws ApplicantException;
}
