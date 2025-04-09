package applicant.statistics.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validates a date-time string
 */
public class DateTimeValidator implements Validation {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void validate(String datetime) throws ApplicantException {
        try {
            LocalDateTime.parse(datetime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw ApplicantException.invalidDate(datetime);
        }
    }
}