package co.com.auth.api.user;

import co.com.auth.api.config.ApplicationRoute;
import co.com.auth.api.user.dto.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class UserRouter {

    private final ApplicationRoute route;

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/users",
                    produces = {"application/json"},
                    method = RequestMethod.POST,
                    beanClass = UserHandler.class,
                    beanMethod = "listenPOSTUseCase",
                    operation = @Operation(
                            operationId = "createUser",
                            summary = "Create a new user",
                            description = "Registers a user in the system",
                            tags = {"Users"},
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Datos del usuario a crear",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                                    implementation = CreateUserDTO.class
                                            )

                                    )
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return RouterFunctions.route().nest(accept(MediaType.APPLICATION_JSON),
                        builder -> builder
                                .POST(route.getUser(), userHandler::listenPOSTUseCase)
                                .GET(route.getSolicited(), userHandler::listenGETExistingId))
                .build();
    }
}
