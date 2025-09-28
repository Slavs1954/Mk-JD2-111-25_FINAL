package by.it_academy.jd2.client;


import by.it_academy.jd2.config.UserServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserClientConfig {

    @Bean
    public WebClient userWebClient(WebClient.Builder builder, UserServiceProperties userServiceProperties) {
        return builder
                .baseUrl(userServiceProperties.getBaseUrl())
                .build();
    }
}
