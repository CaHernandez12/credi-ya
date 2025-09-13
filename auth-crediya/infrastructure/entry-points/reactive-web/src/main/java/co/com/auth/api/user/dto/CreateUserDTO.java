package co.com.auth.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message="El nombre no debe estar vacio")
    private String name;
    @NotBlank(message="El apellido no debe estar vacio")
    private String lastName;
    @NotNull(message="La fecha de nacimiento no debe estar vacio")
    private LocalDate birthDate;
    @NotBlank(message="La direccion no debe estar vacio")
    private String address;
    @NotBlank(message="El telefono no debe estar vacio")
    private String phone;
    @NotBlank(message="El tipo de documento no debe estar vacio")
    private String documentType;
    @NotBlank
    private String documentNumber;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private BigDecimal baseSalary;
    private String password;
    @NotBlank
    private Long roleId;
}
