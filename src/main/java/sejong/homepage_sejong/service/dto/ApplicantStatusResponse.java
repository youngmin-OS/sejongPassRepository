package sejong.homepage_sejong.service.dto;

import sejong.homepage_sejong.domain.ApprovalStatus;

public class ApplicantStatusResponse {

    private ApprovalStatus status;
    private String message;
    private String openKakaoUrl;
    private String meetingPlace;
    private String rejectReason;

    public ApplicantStatusResponse(ApprovalStatus status, String message,
                                   String openKakaoUrl, String meetingPlace, String rejectReason) {
        this.status = status;
        this.message = message;
        this.openKakaoUrl = openKakaoUrl;
        this.meetingPlace = meetingPlace;
        this.rejectReason = rejectReason;
    }

    public ApprovalStatus getStatus() { return status; }
    public String getMessage() { return message; }
    public String getOpenKakaoUrl() { return openKakaoUrl; }
    public String getMeetingPlace() { return meetingPlace; }
    public String getRejectReason() { return rejectReason; }
}
