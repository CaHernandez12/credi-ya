package co.com.auth.commons.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LoggingHelper {

    private static final Logger log = LogManager.getLogger(LoggingHelper.class);

    public Mono<ServerResponse> logRequestResponse(ServerRequest request, Mono<ServerResponse> flow) {
        log.info("REQUEST IN: {} {} - Headers: {}",
                request.method(),
                request.path(),
                request.headers().asHttpHeaders());

        return flow
                .doOnSuccess(response -> log.info("RESPONSE OUT: {} - Status: {}",
                        request.path(), response.statusCode()))
                .doOnError(error -> log.error("ERROR PROCESSING {}: {}",
                        request.path(), error.getMessage()));
    }

    public void logResponseBody(Object body) {
        log.info("RESPONSE BODY: {}", body);
    }


}
