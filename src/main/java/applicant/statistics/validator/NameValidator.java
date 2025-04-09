package applicant.statistics.validator;

/**
 * Validates the full name of an applicant.
 * The name must contain at least a first name and a last name.
 */
public class NameValidator implements Validation {
    @Override
    public void validate(String value) throws ApplicantException {
        if (value == null || value.trim().isEmpty()) {
            throw ApplicantException.invalidName("null or empty");
        }

        String[] parts = value.trim().split("\\s+");
        if (parts.length < 2) {
            throw ApplicantException.invalidName(value);
        }
    }
}
