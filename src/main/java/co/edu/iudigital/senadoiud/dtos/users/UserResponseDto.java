package co.edu.iudigital.senadoiud.dtos.users;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UserResponseDto {

    Long id;

    String email;

    String name;

    String lastName;

    String departmentType;

    String politicalPartie;

    String image;

    String role;

}
