package by.it_academy.jd2.config.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "classifier")
@Data
public class ClassifierProperties {
    private String baseUrl;

}
