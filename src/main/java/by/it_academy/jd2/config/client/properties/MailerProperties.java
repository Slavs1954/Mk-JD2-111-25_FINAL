package by.it_academy.jd2.config.client.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mailer")
@Data
public class MailerProperties {
    private String baseUrl;

}
