package applicant.statistics.repository;
import java.io.InputStream;
import java.util.List;

/**
 * Interface for reading applicant data from input source.
 */
public interface ApplicantRepository {
    List<String[]> readRawApplicantLines(InputStream csvStream);
}