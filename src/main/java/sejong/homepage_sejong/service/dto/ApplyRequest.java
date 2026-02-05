package sejong.homepage_sejong.service.dto;

public class ApplyRequest {
    private String name;
    private String phoneNumber;
    private Integer admissionYear;

    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public Integer getAdmissionYear() { return admissionYear; }

    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAdmissionYear(Integer admissionYear) { this.admissionYear = admissionYear; }
}
