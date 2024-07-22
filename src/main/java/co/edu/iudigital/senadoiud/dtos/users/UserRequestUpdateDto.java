package co.edu.iudigital.senadoiud.dtos.users;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UserRequestUpdateDto implements Serializable {

    static final long serialVersionUID = 1L;

    String name;

    String lastName;

    String password;

}
