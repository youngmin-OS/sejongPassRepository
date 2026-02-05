package sejong.homepage_sejong.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applicants", indexes = {
        @Index(name = "idx_phone", columnList = "phoneNumber")
})
public class Applicant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private Integer admissionYear;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime reviewedAt;

    private String rejectReason;
    private String openKakaoUrl;
    private String meetingPlace;

    protected Applicant() {}

    public Applicant(String name, String phoneNumber, Integer admissionYear) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.admissionYear = admissionYear;
    }

    public void approve(String openKakaoUrl, String meetingPlace) {
        this.status = ApprovalStatus.APPROVED;
        this.reviewedAt = LocalDateTime.now();
        this.openKakaoUrl = openKakaoUrl;
        this.meetingPlace = meetingPlace;
        this.rejectReason = null;
    }

    public void reject(String reason) {
        this.status = ApprovalStatus.REJECTED;
        this.reviewedAt = LocalDateTime.now();
        this.rejectReason = reason;
        this.openKakaoUrl = null;
        this.meetingPlace = null;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public Integer getAdmissionYear() { return admissionYear; }
    public ApprovalStatus getStatus() { return status; }
    public String getRejectReason() { return rejectReason; }
    public String getOpenKakaoUrl() { return openKakaoUrl; }
    public String getMeetingPlace() { return meetingPlace; }
}
