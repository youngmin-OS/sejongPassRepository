package sejong.homepage_sejong.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.upload")
public class FileUploadProperties {

    private String dir = "uploads";
    private long maxSize = 10 * 1024 * 1024; // 10MB

    public String getDir() {
        return dir;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
