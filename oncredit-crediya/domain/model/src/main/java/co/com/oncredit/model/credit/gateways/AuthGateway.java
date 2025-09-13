package co.com.oncredit.model.credit.gateways;

import co.com.oncredit.model.User.User;
import reactor.core.publisher.Mono;

public interface AuthGateway {

    Mono<User> findById(Long userId);
}
