package co.edu.iudigital.senadoiud.services.ifaces;

import co.edu.iudigital.senadoiud.dtos.projects.ProjectRequestDto;
import co.edu.iudigital.senadoiud.dtos.projects.ProjectResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IProjectService {

    ProjectResponseDto createProject(ProjectRequestDto projectRequestDto, Authentication authentication) throws RestException;

    ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto, Authentication authentication) throws RestException;

    ProjectResponseDto searchProjectById(Long id) throws RestException;

    List<ProjectResponseDto> searchProjects() throws RestException;

}
