package co.edu.iudigital.senadoiud.dtos.projects;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class ProjectRequestDto implements Serializable {

    static final long serialVersionUID = 1L;

    @NotNull(message = "Nombre requerido")
    String name;

    @NotNull(message = "Descripcion requerida")
    String description;

}
