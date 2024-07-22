package co.edu.iudigital.senadoiud.services.impl;

import co.edu.iudigital.senadoiud.dtos.projects.ProjectRequestDto;
import co.edu.iudigital.senadoiud.dtos.projects.ProjectResponseDto;
import co.edu.iudigital.senadoiud.exceptions.BadRequestException;
import co.edu.iudigital.senadoiud.exceptions.ErrorDto;
import co.edu.iudigital.senadoiud.exceptions.ForbiddenException;
import co.edu.iudigital.senadoiud.exceptions.InternalServerErrorException;
import co.edu.iudigital.senadoiud.exceptions.NotFoundException;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.models.PoliticalPartie;
import co.edu.iudigital.senadoiud.models.Project;
import co.edu.iudigital.senadoiud.models.UserEntity;
import co.edu.iudigital.senadoiud.repositories.IProjectRepository;
import co.edu.iudigital.senadoiud.repositories.IUserRepository;
import co.edu.iudigital.senadoiud.services.ifaces.IProjectService;
import co.edu.iudigital.senadoiud.utils.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements IProjectService {


    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto, Authentication authentication) throws RestException {
        log.info("Create Project Request");

        String email = authentication.getName();


        UserEntity user = userRepository.findByEmail(email);
        if(user == null ) {
            throw new NotFoundException(
                    ErrorDto.builder()
                            .error("Usuario No encontrado")
                            .message("Usuario No existe")
                            .status(404)
                            .date(LocalDateTime.now())
                            .build());
        }
        try{
            Project project = projectMapper.toProject(projectRequestDto);
            project.setUser(user);
            project = projectRepository.save(project);
            return projectMapper.toProjectResponseDto(project);
        }catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto, Authentication authentication) throws RestException {

        log.info("Update Project Request");

        String email = authentication.getName();


        UserEntity user = userRepository.findByEmail(email);
        if(user == null ) {
            throw new NotFoundException(
                    ErrorDto.builder()
                            .error("Usuario No encontrado")
                            .message("Usuario No existe")
                            .status(404)
                            .date(LocalDateTime.now())
                            .build());
        }

        UserEntity userEntity = userRepository.findByEmail(authentication.getName());

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorDto.builder()
                                        .error("Proyecto no encontrado")
                                        .message("Proyecto no existe")
                                        .status(404)
                                        .date(LocalDateTime.now())
                                        .build())
                );

        if(project.getUser().getId() != userEntity.getId()) {
            throw new ForbiddenException(
                    ErrorDto.builder()
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .message("No está autorizado para editar este proyecto")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .date(LocalDateTime.now())
                            .build());
        }


        project.setName(projectRequestDto.getName() != null ? projectRequestDto.getName() : project.getName());
        project.setDescription(projectRequestDto.getDescription() != null ? projectRequestDto.getDescription() : project.getDescription());

        try {
            project = projectRepository.save(project);
            return projectMapper.toProjectResponseDto(project);
        }catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Override
    public ProjectResponseDto searchProjectById(Long id) throws RestException {

        log.info("Search Project by ID Service");

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorDto.builder()
                                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                        .message("Proyecto no existe")
                                        .status(404)
                                        .date(LocalDateTime.now())
                                        .build())
                );

        try{
            return projectMapper.toProjectResponseDto(project);
        }catch (Exception e){
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message(e.getMessage())
                            .date(LocalDateTime.now())
                            .build()
            );
        }

    }

    @Override
    public List<ProjectResponseDto> searchProjects() throws RestException {

        log.info("Search all Projects Service");

        List<Project> projectList = projectRepository.findAll();
        return projectMapper.toProjectDtoList(projectList);
    }
}
