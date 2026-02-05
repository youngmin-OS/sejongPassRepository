package sejong.homepage_sejong.repository;

import sejong.homepage_sejong.domain.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

    Optional<UploadFile> findTopByApplicantIdOrderByCreatedAtDesc(Long applicantId);
}
