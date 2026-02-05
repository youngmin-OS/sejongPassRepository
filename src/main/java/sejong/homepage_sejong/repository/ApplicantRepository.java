package sejong.homepage_sejong.repository;

import sejong.homepage_sejong.domain.Applicant;
import sejong.homepage_sejong.domain.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Optional<Applicant> findTopByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);

    List<Applicant> findByStatus(ApprovalStatus status);
}
