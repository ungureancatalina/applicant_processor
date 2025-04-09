package applicant.statistics.service.util;

import applicant.statistics.domain.Applicant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to calculate average of the top half based on raw scores.
 */
public class ScoreAverager {

    public static BigDecimal calculateTopHalfAverage(Collection<Applicant> applicants) {
        List<Double> sortedScores = applicants.stream()
                .map(Applicant::getScore)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int half = (sortedScores.size() + 1) / 2;
        double avg = sortedScores.stream()
                .limit(half)
                .mapToDouble(Double::doubleValue)
                .average().orElse(0.0);

        return BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP);
    }

}
