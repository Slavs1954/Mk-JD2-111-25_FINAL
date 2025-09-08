package by.it_academy.jd2.config.client;

import by.it_academy.jd2.config.client.properties.MailerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MailClientConfig {

    @Bean
    public WebClient mailWebClient(WebClient.Builder builder, MailerProperties mailerProperties) {
        return builder
                .baseUrl(mailerProperties.getBaseUrl())
                .build();
    }
}
