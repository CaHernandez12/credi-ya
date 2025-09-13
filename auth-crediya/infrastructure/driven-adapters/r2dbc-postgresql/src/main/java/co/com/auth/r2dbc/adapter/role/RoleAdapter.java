package co.com.auth.r2dbc.adapter.role;

import co.com.auth.commons.exception.TechnicalException;
import co.com.auth.commons.exception.TechnicalExceptionMessage;
import co.com.auth.model.role.Role;
import co.com.auth.model.role.gateways.RoleGateway;
import co.com.auth.r2dbc.adapter.role.data.Mapper;
import co.com.auth.r2dbc.repository.role.ReactiveRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
@RequiredArgsConstructor
public class RoleAdapter implements RoleGateway {

    private final ReactiveRoleRepository repository;

    @Override
    public Mono<Role> findById(Long roleId) {

        return repository.findById(roleId)
                .switchIfEmpty(Mono.empty())
                .map(Mapper::toModel)
                .onErrorMap(e -> new TechnicalException(e, TechnicalExceptionMessage.DATABASE_ERROR));
    }

}
