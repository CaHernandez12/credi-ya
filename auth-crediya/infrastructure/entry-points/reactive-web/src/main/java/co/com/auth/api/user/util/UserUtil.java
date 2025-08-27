package co.com.auth.api.user.util;

import co.com.auth.api.user.dto.CreateUserDTO;
import co.com.auth.model.user.User;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class UserUtil {

    public Mono<User> buildUser(CreateUserDTO createUserDTO){
        User user = new User.Builder()
                .name(createUserDTO.getName())
                .lastName(createUserDTO.getLastName())
                .birthDate(createUserDTO.getBirthDate())
                .address(createUserDTO.getAddress())
                .phone(createUserDTO.getPhone())
                .documentNumber(createUserDTO.getDocumentNumber())
                .documentType(createUserDTO.getDocumentType())
                .email(createUserDTO.getEmail())
                .baseSalary(createUserDTO.getBaseSalary())
                .build();
        return Mono.just(user);
    }
}
