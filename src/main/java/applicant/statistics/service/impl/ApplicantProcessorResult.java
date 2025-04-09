package applicant.statistics.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO class used for final result JSON structure.
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
