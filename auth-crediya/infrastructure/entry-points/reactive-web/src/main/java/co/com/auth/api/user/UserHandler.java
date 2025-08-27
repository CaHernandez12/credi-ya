package co.com.auth.api.user;

import co.com.auth.api.user.dto.CreateUserDTO;
//import co.com.auth.api.user.mapper.UserDTOMapper;
import co.com.auth.api.user.util.UserUtil;
import co.com.auth.usecase.user.UserUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

//    private final UserDTOMapper userDTOMapper;
    private final UserUseCaseService userUseCaseService;

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserDTO.class)
                .flatMap(UserUtil::buildUser)
                .flatMap(userUseCaseService::save)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}

//    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
//        // useCase.logic();
//        return ServerResponse.ok().bodyValue("");
//    }
//
//    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
//        // useCase2.logic();
//        return ServerResponse.ok().bodyValue("");
//    }
//
//    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
//        // useCase.logic();
//        return ServerResponse.ok().bodyValue("");
//    }
//}
