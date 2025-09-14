package by.it_academy.jd2.service;

import by.it_academy.jd2.service.api.ICommonJwtService;
import by.it_academy.jd2.service.api.IMailServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MailServiceClient implements IMailServiceClient {
    private final WebClient mailWebClient;
    private final ICommonJwtService jwtService;

    @Override
    public void add(UUID userId) {
        String jwt = jwtService.generateServiceToken("UserService");
        Boolean response = mailWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("userId", userId)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    @Override
    public boolean verify(UUID userId, String code) {
        String jwt = jwtService.generateServiceToken("UserService");
        Boolean response = mailWebClient.put()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("userId", userId)
                        .queryParam("code", code)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return Boolean.TRUE.equals(response);

    }

    @Override
    public boolean isVerified(UUID userId) {
        String jwt = jwtService.generateServiceToken("UserService");
        Boolean response = mailWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("userId", userId)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return Boolean.TRUE.equals(response);
    }
}
