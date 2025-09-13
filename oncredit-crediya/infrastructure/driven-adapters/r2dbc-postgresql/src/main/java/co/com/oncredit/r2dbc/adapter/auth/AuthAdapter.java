package co.com.oncredit.r2dbc.adapter.auth;

import co.com.oncredit.model.User.User;
import co.com.oncredit.model.credit.gateways.AuthGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthAdapter implements AuthGateway {


    private final WebClient webClient;

    public AuthAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
    }

    @Override
    public Mono<User> findById(Long userId) {
        log.info("Calling auth with userID: {}", userId);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/verifyid")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToMono(UserDTO.class)
                .map(dto -> new User(dto.getEmail(), dto.getDocumentNumber()))
                .doOnNext(user -> log.info("User found: {}", user));
    }
}
