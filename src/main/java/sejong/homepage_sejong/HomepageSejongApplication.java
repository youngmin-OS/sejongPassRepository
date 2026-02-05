package sejong.homepage_sejong;

import sejong.homepage_sejong.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileUploadProperties.class)
public class HomepageSejongApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomepageSejongApplication.class, args);
	}
}
