package co.com.oncredit.r2dbc.adapter.credit.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("tbl_credit")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditData {

    @Id
    @Column("credit_id")
    private Long creditId;
    @Column("amount")
    private BigDecimal amount;
    @Column("term")
    private Integer term;
    @Column("email")
    private String email;
    @Column("document_number")
    private String documentNumber;
    @Column("state_id")
    private Long stateId;
    @Column("loan_type_id")
    private Long loanTypeId;

}


