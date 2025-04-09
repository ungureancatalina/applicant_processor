package applicant.statistics.service;

import applicant.statistics.service.impl.ApplicantProcessorResult;

import java.io.InputStream;

/**
 * Interface for processing applicant data from input source.
 */
public interface ApplicantService {
    ApplicantProcessorResult processApplicants(InputStream csvStream);
}
