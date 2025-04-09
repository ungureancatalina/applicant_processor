package applicant.statistics.service;

import applicant.statistics.repository.ApplicantRepository;
import applicant.statistics.service.impl.ApplicantProcessorResult;
import applicant.statistics.service.impl.ApplicantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantServiceImplTest {

    private ApplicantServiceImpl service;

    @BeforeEach
    void setUp() {
        ApplicantRepository dummyRepo = csvStream -> List.of(
                new String[]{"Ana Pop", "ana@example.com", "2025-04-07T11:00:00", "8.5"},
                new String[]{"Popescu", "", "2025-04-07T10:00:00", "7.0"}
        );
        service = new ApplicantServiceImpl(dummyRepo);
    }

    @Test
    void shouldCountValidAndInvalidApplicantsCorrectly() {
        String dummy = "dummy";
        InputStream stream = new ByteArrayInputStream(dummy.getBytes(StandardCharsets.UTF_8));

        ApplicantProcessorResult result = service.processApplicants(stream);
        assertEquals(2, service.getTotalRows());
        assertEquals(1, service.getValidRows());
        assertEquals(1, service.getSkippedRows());
        assertEquals(1, result.getUniqueApplicants());
    }

    @Test
    void shouldResetCountersCorrectly() {
        service.resetCounters();
        assertEquals(0, service.getTotalRows());
        assertEquals(0, service.getValidRows());
        assertEquals(0, service.getSkippedRows());
    }

}
