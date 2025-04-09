package applicant.statistics.service.util;

import applicant.statistics.domain.Applicant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Utility class for applying score bonuses and penalties to applicants.
 */
public class ScoreAdjuster {

    public static void apply(Collection<Applicant> applicants) {
        LocalDate minDay = applicants.stream()
                .map(Applicant::getDeliveryDateTime)
                .map(LocalDateTime::toLocalDate)
                .min(LocalDate::compareTo)
                .orElse(null);

        LocalDate maxDay = applicants.stream()
                .map(Applicant::getDeliveryDateTime)
                .map(LocalDateTime::toLocalDate)
                .max(LocalDate::compareTo)
                .orElse(null);

        if (minDay == null || maxDay == null || minDay.equals(maxDay)) return;

        for (Applicant a : applicants) {
            LocalDate date = a.getDeliveryDateTime().toLocalDate();
            int hour = a.getDeliveryDateTime().getHour();

            if (date.equals(minDay)) {
                a.setAdjustedScore(Math.min(10.0, a.getScore() + 1.0));
            } else if (date.equals(maxDay) && hour >= 12) {
                a.setAdjustedScore(Math.max(0.0, a.getScore() - 1.0));
            }
        }
    }
}