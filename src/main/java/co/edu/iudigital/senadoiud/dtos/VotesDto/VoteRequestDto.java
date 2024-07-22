package co.edu.iudigital.senadoiud.dtos.VotesDto;

import co.edu.iudigital.senadoiud.enums.DepartmentType;
import co.edu.iudigital.senadoiud.enums.VoteType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class VoteRequestDto implements Serializable {

    static final long serialVersionUID = 1L;

    @NotNull(message = "Voto requerido")
    VoteType voteType;

}
