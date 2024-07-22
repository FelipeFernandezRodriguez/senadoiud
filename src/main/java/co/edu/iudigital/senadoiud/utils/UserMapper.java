package co.edu.iudigital.senadoiud.utils;

import co.edu.iudigital.senadoiud.dtos.users.UserRequestDto;
import co.edu.iudigital.senadoiud.dtos.users.UserResponseDto;
import co.edu.iudigital.senadoiud.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUser(UserRequestDto userRequestDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setName(userRequestDto.getName());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setPassword(userRequestDto.getPassword());
        userEntity.setDepartmentType(userRequestDto.getDepartmentType());
        userEntity.setPoliticalPartie(userRequestDto.getPoliticalPartie());
        userEntity.setRole(userRequestDto.getRole());
        userEntity.setImage(userRequestDto.getImage());
        return userEntity;
    }

    public UserResponseDto toUserResponseDto(UserEntity userEntity){
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .departmentType(userEntity.getDepartmentType().getName())
                .politicalPartie(userEntity.getPoliticalPartie().getName())
                .image(userEntity.getImage())
                .role(userEntity.getRole().getName())
                .build();
    }

}
