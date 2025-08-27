package co.com.auth.api.user;

import co.com.auth.api.config.ApplicationRoute;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class UserRouter {

    private final ApplicationRoute route;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return RouterFunctions.route().nest(accept(MediaType.APPLICATION_JSON),
                builder -> builder
                        .POST(route.getUser(), userHandler::listenPOSTUseCase))
                .build();

//        return route(GET("/api/usecase/path"), userHandler::listenGETUseCase)
//                .andRoute(POST("/api/usecase/otherpath"), userHandler::listenPOSTUseCase)
//                .and(route(GET("/api/otherusercase/path"), userHandler::listenGETOtherUseCase));
    }
}
