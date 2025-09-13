package co.com.auth.usecase.role;

import co.com.auth.model.role.Role;
import co.com.auth.model.role.gateways.RoleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RoleUseCase implements RoleUsecaseService{

    private final RoleGateway roleGateway;

    @Override
    public Mono<Role> findById(Long id) {
        return roleGateway.findById(id);
    }
}
