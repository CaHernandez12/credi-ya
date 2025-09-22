package co.com.auth.r2dbc.adapter.user.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;



@Table(value = "tbl_user", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserData {

    @Id
    @Column("user_id")
    private Long userId;
    @Column("name")
    private String name;
    @Column("last_name")
    private String lastName;
    @Column("birth_date")
    private LocalDate birthDate;
    @Column("address")
    private String address;
    @Column("phone")
    private String phone;
    @Column("email")
    private String email;
    @Column("document_type")
    private String documentType;
    @Column("document_number")
    private String documentNumber;
    @Column("base_salary")
    private BigDecimal baseSalary;
    @Column("password")
    private String password;
    @Column("role_id")
    private Long roleId;

}
