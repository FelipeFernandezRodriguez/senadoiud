/*package co.edu.iudigital.senadoiud.controllers;

import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteRequestDto;
import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.services.ifaces.IVoteService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Project Controller", description = "Controlador para gestión de los votos")
@Slf4j
@RestController
@RequestMapping("/votes/")
public class VoteController {

    @Autowired
    private IVoteService voteService;

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
    @PostMapping("{id}")
    public ResponseEntity<VoteResponseDto> voteProject(
            @Valid @PathVariable Long id,
            Authentication authentication,
            @RequestBody VoteRequestDto voteRequestDto
    ) throws RestException {
        log.info("Ejecutando create de ProjectController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(voteService.voteProject(id, authentication, voteRequestDto));
    }
}*/
