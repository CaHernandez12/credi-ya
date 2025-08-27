package co.com.auth.r2dbc.adapter.user.data;

import co.com.auth.model.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public UserData toData(User user) {
        return UserData.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .phone(user.getPhone())
                .documentNumber(user.getDocumentNumber())
                .documentType(user.getDocumentType())
                .email(user.getEmail())
                .baseSalary(user.getBaseSalary())

                .build();
    }

    public User toModel(UserData data) {
        return new User.Builder()
                .name(data.getName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .address(data.getAddress())
                .phone(data.getPhone())
                .documentNumber(data.getDocumentNumber())
                .documentType(data.getDocumentType())
                .email(data.getEmail())
                .baseSalary(data.getBaseSalary())
                .build();
    }
}
