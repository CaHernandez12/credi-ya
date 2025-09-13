package co.com.auth.r2dbc.adapter.role.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tbl_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleData {

    @Id
    @Column("role_id")
    private Long roleId;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
}
