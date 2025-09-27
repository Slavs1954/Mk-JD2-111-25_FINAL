package by.it_academy.jd2.config.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "audit")
@Data
public class AuditProperties {
    private String baseUrl;
}
