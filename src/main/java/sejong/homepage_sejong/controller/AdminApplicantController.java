package sejong.homepage_sejong.controller;

import sejong.homepage_sejong.domain.Applicant;
import sejong.homepage_sejong.domain.UploadFile;
import sejong.homepage_sejong.domain.ApprovalStatus;
import sejong.homepage_sejong.service.ApplicantService;
import sejong.homepage_sejong.service.FileStorageService;
import sejong.homepage_sejong.service.dto.ReviewRequest;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApplicantController {

    private final ApplicantService service;
    private final FileStorageService fileStorageService;

    public AdminApplicantController(ApplicantService s, FileStorageService f) {
        this.service = s;
        this.fileStorageService = f;
    }

    @PostMapping("/review/{id}")
    public void review(@PathVariable Long id, @RequestBody ReviewRequest req) {
        service.review(id, req);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<?> download(@PathVariable Long id) throws Exception {
        UploadFile f = service.getFile(id);
        Path path = fileStorageService.load(f.getStoredFilename());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(f.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + f.getOriginalFilename() + "\"")
                .body(new UrlResource(path.toUri()));
    }

    @GetMapping("/applicants")
    public List<Applicant> list(@RequestParam(required = false) ApprovalStatus status) {
        return status == null ? service.listAll() : service.listByStatus(status);
    }


}
