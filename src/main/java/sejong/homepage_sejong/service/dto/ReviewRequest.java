package sejong.homepage_sejong.service.dto;

public class ReviewRequest {

    private String action;
    private String openKakaoUrl;
    private String meetingPlace;
    private String rejectReason;

    public String getAction() { return action; }
    public String getOpenKakaoUrl() { return openKakaoUrl; }
    public String getMeetingPlace() { return meetingPlace; }
    public String getRejectReason() { return rejectReason; }
}
