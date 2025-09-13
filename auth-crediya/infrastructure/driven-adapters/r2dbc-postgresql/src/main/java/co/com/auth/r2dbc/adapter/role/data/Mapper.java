package co.com.auth.r2dbc.adapter.role.data;

import co.com.auth.model.role.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public Role toModel(RoleData roleData) {
        return new Role.Builder()
                .roleId(roleData.getRoleId())
                .name(roleData.getName())
                .description(roleData.getDescription())
                .build();
    }
}
