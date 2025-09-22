package co.com.auth.api.mapper;

import co.com.auth.api.login.dto.LoginRequestDTO;
import co.com.auth.api.login.dto.LoginResponseDTO;
import co.com.auth.model.auth.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginDTOMapper {

    Login toModel(LoginRequestDTO loginRequestDTO);
    LoginResponseDTO toResponse(String token);
}
