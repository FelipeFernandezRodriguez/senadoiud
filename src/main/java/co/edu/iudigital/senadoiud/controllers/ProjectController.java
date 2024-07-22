package co.edu.iudigital.senadoiud.controllers;

import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieRequestDto;
import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieResponseDto;
import co.edu.iudigital.senadoiud.dtos.projects.ProjectRequestDto;
import co.edu.iudigital.senadoiud.dtos.projects.ProjectResponseDto;
import co.edu.iudigital.senadoiud.dtos.users.UserRequestEnabledDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.models.Project;
import co.edu.iudigital.senadoiud.services.ifaces.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Project Controller", description = "Controlador para gestión de los proyectos")
@Slf4j
@RestController
@RequestMapping("/projects/")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Secured("ROLE_USER")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @Operation(summary = "Save political partie",
            description = "Save political partie")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ProjectResponseDto> save(
            @Valid @RequestBody ProjectRequestDto projectRequestDto, Authentication authentication
    ) throws RestException {
        log.info("Ejecutando create de ProjectController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(projectRequestDto, authentication));
    }

    @Secured("ROLE_USER")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @Operation(summary = "Update project",
            description = "Update project")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequestDto projectRequestDto,
            Authentication authentication
    ) throws RestException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.updateProject(id, projectRequestDto, authentication));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @Operation(summary = "Search project by Id",
            description = "Search project by Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> searchById(@PathVariable Long id) throws RestException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.searchProjectById(id));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @Operation(summary = "Search all projects partie",
            description = "Search all projects partie")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> searchAll() throws RestException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.searchProjects());
    }

}
