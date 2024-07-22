package co.edu.iudigital.senadoiud.dtos.users;

import co.edu.iudigital.senadoiud.enums.DepartmentType;
import co.edu.iudigital.senadoiud.models.PoliticalPartie;
import co.edu.iudigital.senadoiud.models.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class UserRequestDto implements Serializable {

    static final long serialVersionUID = 1L;

    @NotNull(message = "Email requerido")
    @Email(message = "Debe ser un Email valido")
    String email;

    @NotNull(message = "Nombre requerido")
    String name;

    @NotNull(message = "Apellido requerido")
    String lastName;

    @NotNull(message = "Contraseña requerida")
    String password;

    @NotNull(message = "Departamento requerido")
    DepartmentType departmentType;

    @NotNull(message = "Partido político requerido")
    PoliticalPartie politicalPartie;

    @NotNull(message = "Rol requerido")
    Role role;

    String image;

}
