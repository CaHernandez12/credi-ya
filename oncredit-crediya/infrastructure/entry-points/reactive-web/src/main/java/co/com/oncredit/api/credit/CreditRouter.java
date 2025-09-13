package co.com.oncredit.api.credit;

import co.com.oncredit.api.config.ApplicationRoute;
import co.com.oncredit.api.credit.dto.CreateCreditDTO;
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
public class CreditRouter {

    private final ApplicationRoute route;

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/solicited",
                    produces = {"application/json"},
                    method = RequestMethod.POST,
                    beanClass = CreditHandler.class,
                    beanMethod = "listenPOSTUseCase",
                    operation = @Operation(
                            operationId = "createCredit",
                            summary = "Create a new Credit",
                            description = "Registers a credit in the system",
                            tags = {"Credits"},
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Datos del credito a crear",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                                    implementation = CreateCreditDTO.class
                                            )
                                    )
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(CreditHandler creditHandler) {
        return RouterFunctions.route().nest(accept(MediaType.APPLICATION_JSON),
                                builder -> builder
                                        .POST(route.getCredit(), creditHandler::listenPOSTUseCase))
                .build();
    }
}
