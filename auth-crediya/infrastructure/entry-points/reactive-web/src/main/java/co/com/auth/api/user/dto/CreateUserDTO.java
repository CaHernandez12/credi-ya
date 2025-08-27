package co.com.auth.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    private String address;
    private String phone;
    @NotBlank
    private String documentType;
    @NotBlank
    private String documentNumber;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private BigDecimal baseSalary;
}
