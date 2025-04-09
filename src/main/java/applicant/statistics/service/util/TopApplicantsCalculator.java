package applicant.statistics.service.util;

import applicant.statistics.domain.Applicant;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to extract top applicants sorted by score.
 */
public class TopApplicantsCalculator {

    public static List<String> extractTop3LastNames(Collection<Applicant> applicants) {
        return applicants.stream()
                .sorted(Comparator.comparingDouble(Applicant::getAdjustedScore).reversed()
                        .thenComparingDouble(Applicant::getScore).reversed()
                        .thenComparing(Applicant::getDeliveryDateTime)
                        .thenComparing(Applicant::getEmail))
                .limit(3)
                .map(Applicant::getLastName)
                .collect(Collectors.toList());
    }
}
