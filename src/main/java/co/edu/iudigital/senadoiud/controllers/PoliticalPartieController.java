package co.edu.iudigital.senadoiud.controllers;

import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieRequestDto;
import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.services.ifaces.IPoliticalPartieService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Political Partie Controller", description = "Controlador para gestión de los partidos políticos")
@Slf4j
@RestController
@RequestMapping("/politicalparties/")
public class PoliticalPartieController {


    @Autowired
    private IPoliticalPartieService politicalPartieService;

    @Secured("ROLE_ADMIN")
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
    public ResponseEntity<PoliticalPartieResponseDto> save(
            @Valid @RequestBody PoliticalPartieRequestDto politicalPartieRequestDto
    ) throws RestException {
        log.info("Ejecutando create de PoliticalPartieController");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(politicalPartieService.createPoliticalPartie(politicalPartieRequestDto));
    }

    @Secured("ROLE_ADMIN")
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
    @Operation(summary = "Update political partie",
            description = "Update political partie")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<PoliticalPartieResponseDto> update(
            @PathVariable Long id,
            @RequestBody PoliticalPartieRequestDto politicalPartieRequestDto
    ) throws RestException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(politicalPartieService.updatePoliticalPartie(id, politicalPartieRequestDto));
    }

    @Secured("ROLE_ADMIN")
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
    @Operation(summary = "Delete political partie",
            description = "Delete political partie")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws RestException {
        politicalPartieService.deletePoliticalPartie(id);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @Operation(summary = "Search political partie by Id",
            description = "Search political partie by Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<PoliticalPartieResponseDto> searchById(@PathVariable Long id) throws RestException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(politicalPartieService.searchPoliticalPartieById(id));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @Operation(summary = "Search all political partie",
            description = "Search all political partie")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<PoliticalPartieResponseDto>> searchAll() throws RestException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(politicalPartieService.searchPoliticalParties());
    }

}
