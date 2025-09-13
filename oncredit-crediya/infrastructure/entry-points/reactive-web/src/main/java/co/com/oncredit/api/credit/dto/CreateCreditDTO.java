package co.com.oncredit.api.credit.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditDTO {

    @NotBlank
    private String documentNumber;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private BigDecimal amount;
    @NotNull
    private Integer term;
    @NotNull
    private Long loanTypeId;

}
