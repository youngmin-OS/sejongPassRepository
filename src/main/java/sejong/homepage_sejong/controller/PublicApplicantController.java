package sejong.homepage_sejong.controller;

import sejong.homepage_sejong.service.ApplicantService;
import sejong.homepage_sejong.service.dto.ApplyRequest;
import sejong.homepage_sejong.service.dto.ApplicantStatusResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public")
public class PublicApplicantController {

    private final ApplicantService service;

    public PublicApplicantController(ApplicantService service) {
        this.service = service;
    }

    @PostMapping(value = "/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void apply(@RequestPart("data") ApplyRequest req,
                      @RequestPart("file") MultipartFile file) throws Exception {
        service.apply(req, file);
    }

    @GetMapping("/status")
    public ApplicantStatusResponse status(@RequestParam String phone) {
        return service.getStatus(phone);
    }
}
