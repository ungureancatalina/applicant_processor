package applicant.statistics.service;

import applicant.statistics.service.impl.ApplicantProcessorResult;

import java.io.InputStream;

public interface ApplicantService {
    ApplicantProcessorResult processApplicants(InputStream csvStream);
}
