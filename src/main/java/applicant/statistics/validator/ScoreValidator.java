package applicant.statistics.validator;

/**
 * Validates the score of an applicant.
 * Must be a number between 0 and 10 with up to two decimal places.
 */
public class ScoreValidator implements Validation {
    @Override
    public void validate(String scoreStr) throws ApplicantException {
        try {
            double score = Double.parseDouble(scoreStr);
            if (score < 0 || score > 10) {
                throw ApplicantException.invalidScore(scoreStr);
            }
        } catch (NumberFormatException e) {
            throw ApplicantException.invalidScore(scoreStr);
        }
    }
}