package applicant.statistics.validator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validates a date-time string in ISO local format: yyyy-MM-dd'T'HH:mm:ss
 */
public class DateTimeValidator implements Validation {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void validate(String datetime) throws ApplicantException {
        try {
            FORMATTER.parse(datetime);
        } catch (DateTimeParseException e) {
            throw ApplicantException.invalidDate(datetime);
        }
    }
}