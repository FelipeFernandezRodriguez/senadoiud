package co.edu.iudigital.senadoiud.utils;

import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieResponseDto;
import co.edu.iudigital.senadoiud.dtos.projects.ProjectRequestDto;
import co.edu.iudigital.senadoiud.dtos.projects.ProjectResponseDto;
import co.edu.iudigital.senadoiud.models.PoliticalPartie;
import co.edu.iudigital.senadoiud.models.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public Project toProject(ProjectRequestDto projectRequestDto) {

        Project project = new Project();
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        return project;

    }

    public ProjectResponseDto toProjectResponseDto(Project project) {

        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .user(project.getUser().getName())
                .build();

    }

    public List<ProjectResponseDto> toProjectDtoList(List<Project> projectList){
        return projectList.stream()
                .map(project -> toProjectResponseDto(project))
                .collect(Collectors.toList());
    }

}
