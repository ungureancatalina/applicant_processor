package applicant.statistics.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an applicant to the internship program.
 * The `Applicant` class holds the details of a single applicant, including:
 * - Full name
 * - Email
 * - Delivery date/time of the application
 * - Raw score (before any adjustments)
 * - Adjusted score (after applying bonus or malus rules based on delivery time)
 *
 * Responsibilities:
 * - Store personal and performance information of an applicant.
 * - Calculate and manage raw and adjusted scores.
 * - Provide utility methods, such as extracting the last name.
 * - Support equality comparison and hashing based on the applicant's email.
 */
public class Applicant {

    private final String fullName;
    private final String email;
    private final LocalDateTime deliveryDateTime;
    private final double score;
    private double adjustedScore;

    public Applicant(String fullName, String email, LocalDateTime deliveryDateTime, double score) {
        this.fullName = Objects.requireNonNull(fullName, "Full name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.deliveryDateTime = Objects.requireNonNull(deliveryDateTime, "Delivery date/time cannot be null");
        this.score = score;
        this.adjustedScore = score;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public double getScore() {
        return score;
    }

    public double getAdjustedScore() {
        return adjustedScore;
    }

    public void setAdjustedScore(double adjustedScore) {
        this.adjustedScore = adjustedScore;
    }

    public String getLastName() {
        String[] parts = fullName.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;
        Applicant that = (Applicant) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", deliveryDateTime=" + deliveryDateTime +
                ", score=" + score +
                ", adjustedScore=" + adjustedScore +
                '}';
    }
}