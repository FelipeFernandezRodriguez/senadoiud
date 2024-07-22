package co.edu.iudigital.senadoiud.dtos.politicalPartie;

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
public class PoliticalPartieRequestDto implements Serializable {

    static final long serialVersionUID = 1L;

    @NotNull(message = "Nombre requerido")
    String name;

    @NotNull(message = "Slogan requerido")
    String slogan;

    @NotNull(message = "Imagen requerida")
    String image;

}
