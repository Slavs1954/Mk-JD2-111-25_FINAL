package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserVerification;
import by.it_academy.jd2.service.api.ICommonJwtService;
import by.it_academy.jd2.service.api.IUserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceClient implements IUserServiceClient {
    private final WebClient userWebClient;
    private final ICommonJwtService commonJwtService;

    @Override
    public List<UserVerification> getUserVerification(List<Pair<UUID, String>> idCodePairs) {
        String jwt = commonJwtService.generateServiceToken("MailerService");
        return userWebClient.post()
                .headers(headers -> headers.setBearerAuth(jwt))
                .bodyValue(idCodePairs)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserVerification>>() {})
                .block();
    }
    public UserVerification getUserVerification(UUID userId, String code) {
        String jwt = commonJwtService.generateServiceToken("MailerService");
        return userWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("userId", userId)
                        .queryParam("code", code)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<UserVerification>() {})
                .block();
    }
}
