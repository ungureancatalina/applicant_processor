package applicant.statistics.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO class used for representing the final results
 * after processing applicant data. This class is structured to hold
 * the relevant information that will be serialized into JSON for output.
 * *
 * Responsibilities:
 * - Holds the number of unique valid applicants.
 * - Holds the list of last names of the top 3 applicants (sorted by their adjusted scores).
 * - Holds the average score of the top half of the applicants (calculated based on raw scores).
 */
public class ApplicantProcessorResult {

    @JsonProperty("uniqueApplicants")
    private final int uniqueApplicants;

    @JsonProperty("topApplicants")
    private final List<String> topApplicants;

    @JsonProperty("averageScore")
    private final BigDecimal averageScore;

    public ApplicantProcessorResult(int uniqueApplicants, List<String> topApplicants, BigDecimal averageScore) {
        this.uniqueApplicants = uniqueApplicants;
        this.topApplicants = topApplicants;
        this.averageScore = averageScore;
    }

    public int getUniqueApplicants() {
        return uniqueApplicants;
    }

    public List<String> getTopApplicants() {
        return topApplicants;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }
}
