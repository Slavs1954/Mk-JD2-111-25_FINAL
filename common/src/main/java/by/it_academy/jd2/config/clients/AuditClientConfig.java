package by.it_academy.jd2.config.clients;


import by.it_academy.jd2.config.security.properties.AuditProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AuditClientConfig {

    @Bean
    public WebClient auditWebClient(WebClient.Builder builder, AuditProperties auditProperties) {
        return builder
                .baseUrl(auditProperties.getBaseUrl())
                .build();
    }
}
