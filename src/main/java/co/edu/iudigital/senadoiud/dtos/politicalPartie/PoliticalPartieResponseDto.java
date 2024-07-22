package co.edu.iudigital.senadoiud.dtos.politicalPartie;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class PoliticalPartieResponseDto {

    String name;

    String slogan;

    String image;

}
