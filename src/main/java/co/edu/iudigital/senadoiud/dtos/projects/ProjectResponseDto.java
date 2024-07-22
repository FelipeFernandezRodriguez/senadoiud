package co.edu.iudigital.senadoiud.dtos.projects;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class ProjectResponseDto {

    Long id;

    String name;

    String description;

    String user;

}
