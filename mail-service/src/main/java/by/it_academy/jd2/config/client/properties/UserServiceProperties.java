package by.it_academy.jd2.config.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "user.service")
@Data
public class UserServiceProperties {
    private String baseUrl;
}
