package sejong.homepage_sejong.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UploadFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Applicant applicant;

    private String originalFilename;
    private String storedFilename;
    private String contentType;
    private long size;

    @Enumerated(EnumType.STRING)
    private StorageType storageType = StorageType.LOCAL;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected UploadFile() {}

    public UploadFile(Applicant applicant, String originalFilename, String storedFilename,
                      String contentType, long size) {
        this.applicant = applicant;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.contentType = contentType;
        this.size = size;
    }

    public Long getId() { return id; }
    public String getStoredFilename() { return storedFilename; }
    public String getOriginalFilename() { return originalFilename; }
    public String getContentType() { return contentType; }
}
