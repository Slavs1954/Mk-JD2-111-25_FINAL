package by.it_academy.jd2;

import by.it_academy.jd2.config.client.properties.UserServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class MailService {
    public static void main(String[] args) {
        SpringApplication.run(MailService.class, args);
    }
}
