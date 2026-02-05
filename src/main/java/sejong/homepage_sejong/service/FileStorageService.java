package sejong.homepage_sejong.service;

import sejong.homepage_sejong.config.FileUploadProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path root;
    private static final Set<String> ALLOWED = Set.of(
            "application/pdf", "image/png", "image/jpeg"
    );

    public FileStorageService(FileUploadProperties props) {
        try {
            this.root = Paths.get(props.getDir()).toAbsolutePath().normalize();
            Files.createDirectories(this.root);
        } catch (Exception e) {
            throw new IllegalStateException("업로드 디렉토리 생성 실패", e);
        }
    }

    public StoredFile store(MultipartFile file) throws Exception {
        if (!ALLOWED.contains(file.getContentType())) {
            throw new IllegalArgumentException("허용되지 않은 파일 형식");
        }

        String stored = UUID.randomUUID().toString();
        Path target = root.resolve(stored);
        Files.copy(file.getInputStream(), target);

        return new StoredFile(
                file.getOriginalFilename(),
                stored,
                file.getContentType(),
                file.getSize()
        );
    }

    public Path load(String storedFilename) {
        return root.resolve(storedFilename);
    }

    public record StoredFile(
            String originalFilename,
            String storedFilename,
            String contentType,
            long size
    ) {}
}
