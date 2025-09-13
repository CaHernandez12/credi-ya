package co.com.auth.api.mapper;

import co.com.auth.api.user.dto.CreateUserDTO;
import co.com.auth.api.user.dto.UserDTOResponse;
import co.com.auth.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    User toModel(CreateUserDTO createUserDTO);

    UserDTOResponse toResponse(User user);
}
