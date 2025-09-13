package co.com.auth.model.role.gateways;

import co.com.auth.model.role.Role;
import reactor.core.publisher.Mono;

public interface RoleGateway {

    Mono<Role> findById(Long roleId);
}
