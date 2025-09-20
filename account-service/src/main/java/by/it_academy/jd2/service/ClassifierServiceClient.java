package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Currency;
import by.it_academy.jd2.dto.OperationCategory;
import by.it_academy.jd2.service.api.IClassifierServiceClient;
import by.it_academy.jd2.service.api.ICommonJwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClassifierServiceClient implements IClassifierServiceClient {
    private final WebClient classifierWebClient;
    private final ICommonJwtService jwtService;


    @Override
    public Currency getCurrency(String title) {
        String jwt = jwtService.generateServiceToken("AccountService");
        return classifierWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/currency")
                        .queryParam("title", title)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(Currency.class)
                .block();
    }

    @Override
    public OperationCategory getCategory(String title) {
        String jwt = jwtService.generateServiceToken("AccountService");
        return classifierWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/category")
                        .queryParam("title", title)
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(OperationCategory.class)
                .block();
    }

    @Override
    public Currency getCurrency(UUID uuid) {
        String jwt = jwtService.generateServiceToken("AccountService");
        return classifierWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/currency/".concat(uuid.toString()))
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(Currency.class)
                .block();
    }

    @Override
    public OperationCategory getCategory(UUID uuid) {
        String jwt = jwtService.generateServiceToken("AccountService");
        return classifierWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/category/".concat(uuid.toString()))
                        .build())
                .headers(headers -> headers.setBearerAuth(jwt))
                .retrieve()
                .bodyToMono(OperationCategory.class)
                .block();
    }
}
