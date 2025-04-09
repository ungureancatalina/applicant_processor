package applicant.statistics.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an applicant to the internship program.
 * Contains the data extracted from the CSV file: full name, email, delivery datetime and raw score.
 * Also holds the adjusted score (after applying bonus/malus rules).
 */
public class Applicant {

    private final String fullName; // Full name: first name + optional middle names + last name
    private final String email; // Unique email identifier
    private final LocalDateTime deliveryDateTime; // Submission timestamp
    private final double score; // Raw score
    private double adjustedScore; // Adjusted score after applying delivery rules

    /**
     * Constructs a new Applicant object.
     *
     * @param fullName          Full name (at least FirstName LastName)
     * @param email             Valid, unique email address
     * @param deliveryDateTime  Date and time of submission
     * @param score             Raw score between 0 and 10
     */
    public Applicant(String fullName, String email, LocalDateTime deliveryDateTime, double score) {
        this.fullName = Objects.requireNonNull(fullName, "Full name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.deliveryDateTime = Objects.requireNonNull(deliveryDateTime, "Delivery date/time cannot be null");
        this.score = score;
        this.adjustedScore = score; // Initial adjusted score is equal to the raw score
    }

    // Getters

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

    // Setters

    public void setAdjustedScore(double adjustedScore) {
        this.adjustedScore = adjustedScore;
    }

    /**
     * Extracts the last name from the full name.
     * Assumes the last token is the last name.
     *
     * @return Last name of the applicant
     */
    public String getLastName() {
        String[] parts = fullName.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    /**
     * Equality is based solely on the email address.
     *
     * @param o the object to compare
     * @return true if both applicants share the same email
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;
        Applicant that = (Applicant) o;
        return email.equals(that.email);
    }

    /**
     * Generates hash code based on email.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * String representation of the applicant. Useful for debugging.
     *
     * @return a readable representation of the applicant
     */
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