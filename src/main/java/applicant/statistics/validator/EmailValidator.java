package applicant.statistics.validator;

/**
 * Validates the email address of an applicant based on strict format rules.
 */
public class EmailValidator implements Validation {
    @Override
    public void validate(String email) throws ApplicantException {
        if (email == null || email.isEmpty()) {
            throw ApplicantException.invalidEmail("null or empty");
        }

        for (char c : email.toCharArray()) {
            if (c > 127) {
                throw ApplicantException.invalidEmail("non-ASCII characters");
            }
        }

        if (!Character.isLetter(email.charAt(0))) {
            throw ApplicantException.invalidEmail("must start with a letter: " + email);
        }

        if (!Character.isLetter(email.charAt(email.length() - 1))) {
            throw ApplicantException.invalidEmail("must end with a letter: " + email);
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex != email.lastIndexOf('@')) {
            throw ApplicantException.invalidEmail("must contain exactly one '@': " + email);
        }

        int dotIndex = email.indexOf('.', atIndex+1);
        if (dotIndex == -1 || dotIndex == atIndex + 1) {
            throw ApplicantException.invalidEmail("dot must follow '@' but not directly: " + email);
        }

        String emailRegex =
                "^[A-Za-z][A-Za-z0-9._-]*@" + "[A-Za-z0-9](?!.*\\.\\.)[A-Za-z0-9.-]*" + "\\.[A-Za-z]{2,}$";

        if (!email.matches(emailRegex)) {
            throw ApplicantException.invalidEmail("Email format is invalid: " + email);
        }
    }
}