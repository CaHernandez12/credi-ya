package co.com.oncredit.r2dbc.adapter.loantype.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("tbl_loan_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoanTypeData {

    @Id
    @Column("loan_type_id")
    private Long loanTypeId;
    @Column("name")
    private String name;
    @Column("min_amount")
    private BigDecimal minAmount;
    @Column("max_amount")
    private BigDecimal maxAmount;
    @Column("interest_rate")
    private BigDecimal interestRate;
    @Column("automatic_validation")
    private Boolean automaticValidation;

}
