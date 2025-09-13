package co.com.auth.usecase.role;

import co.com.auth.model.role.Role;
import reactor.core.publisher.Mono;

public interface RoleUsecaseService {

    Mono<Role> findById(Long id);
}
