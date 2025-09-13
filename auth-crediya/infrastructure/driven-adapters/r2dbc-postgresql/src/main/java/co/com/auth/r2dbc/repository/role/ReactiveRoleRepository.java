package co.com.auth.r2dbc.repository.role;

import co.com.auth.r2dbc.adapter.role.data.RoleData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveRoleRepository  extends ReactiveCrudRepository<RoleData, Long> {

}
