package applicant.statistics.repository;

/**
 * Custom exception to represent errors encountered during repository operations.
 */
public class RepositoryException extends RuntimeException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RepositoryException failedToReadSource(Throwable cause) {
        return new RepositoryException("Failed to read applicants from input source.", cause);
    }

    public static RepositoryException unexpectedData(String detail) {
        return new RepositoryException("Unexpected data encountered in input: " + detail);
    }

    public static RepositoryException nullInputStream() {
        return new RepositoryException("Input stream provided to repository is null.");
    }
}
