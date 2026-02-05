package sejong.homepage_sejong.service;

import sejong.homepage_sejong.domain.*;
import sejong.homepage_sejong.repository.*;
import sejong.homepage_sejong.service.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final UploadFileRepository uploadFileRepository;
    private final FileStorageService fileStorageService;

    public ApplicantService(ApplicantRepository a, UploadFileRepository u, FileStorageService f) {
        this.applicantRepository = a;
        this.uploadFileRepository = u;
        this.fileStorageService = f;
    }

    @Transactional
    public void apply(ApplyRequest req, MultipartFile file) throws Exception {
        Applicant applicant = new Applicant(
                req.getName(),
                req.getPhoneNumber().replaceAll("[^0-9]", ""),
                req.getAdmissionYear()
        );
        applicantRepository.save(applicant);

        var stored = fileStorageService.store(file);
        uploadFileRepository.save(
                new UploadFile(applicant,
                        stored.originalFilename(),
                        stored.storedFilename(),
                        stored.contentType(),
                        stored.size())
        );
    }

    @Transactional(readOnly = true)
    public ApplicantStatusResponse getStatus(String phone) {
        Applicant a = applicantRepository
                .findTopByPhoneNumberOrderByCreatedAtDesc(phone.replaceAll("[^0-9]", ""))
                .orElseThrow();

        return switch (a.getStatus()) {
            case PENDING -> new ApplicantStatusResponse(
                    a.getStatus(), "현재 검토 중입니다.", null, null, null);
            case APPROVED -> new ApplicantStatusResponse(
                    a.getStatus(), "합격을 축하합니다!",
                    a.getOpenKakaoUrl(), a.getMeetingPlace(), null);
            case REJECTED -> new ApplicantStatusResponse(
                    a.getStatus(), "제출 자료가 반려되었습니다.",
                    null, null, a.getRejectReason());
        };
    }

    @Transactional
    public void review(Long id, ReviewRequest req) {
        Applicant a = applicantRepository.findById(id).orElseThrow();
        if ("APPROVE".equals(req.getAction())) {
            a.approve(req.getOpenKakaoUrl(), req.getMeetingPlace());
        } else {
            a.reject(req.getRejectReason());
        }
    }

    public UploadFile getFile(Long applicantId) {
        return uploadFileRepository
                .findTopByApplicantIdOrderByCreatedAtDesc(applicantId)
                .orElseThrow();
    }
}
