package co.com.auth.api.login;

import co.com.auth.api.config.ApplicationRoute;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class AuthRouter {

    private final ApplicationRoute route;

    @Bean
    public RouterFunction<ServerResponse> authFunction(AuthHandler authHandler) {
        return RouterFunctions.route().nest(accept(MediaType.APPLICATION_JSON),
                        builder -> builder
                                .POST(route.getAuth(), authHandler::login))
                .build();
    }

}
