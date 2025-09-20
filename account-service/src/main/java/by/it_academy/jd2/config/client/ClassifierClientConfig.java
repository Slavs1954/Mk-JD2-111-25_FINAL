package by.it_academy.jd2.config.client;

import by.it_academy.jd2.config.client.properties.ClassifierProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClassifierClientConfig {

    @Bean
    public WebClient classifierWebClient(WebClient.Builder builder, ClassifierProperties classifierProperties) {
        return builder
                .baseUrl(classifierProperties.getBaseUrl())
                .build();
    }
}
