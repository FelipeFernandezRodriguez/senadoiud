package co.edu.iudigital.senadoiud.controllers;

import co.edu.iudigital.senadoiud.dtos.users.UserRequestDto;
import co.edu.iudigital.senadoiud.dtos.users.UserRequestEnabledDto;
import co.edu.iudigital.senadoiud.dtos.users.UserResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.services.ifaces.IUserService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "UserEntity Controller", description = "Controlador para gestión de los usuarios")
@Slf4j
@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    @Operation(summary = "Save user",
            description = "Save user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/created")
    public ResponseEntity<UserResponseDto> save (
            @Valid @RequestBody UserRequestDto userRequestDto
    ) throws RestException {
        log.info("Ejecutando create de UserController");
        final String passwordEncoded = passwordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(passwordEncoded);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userRequestDto));
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
    @Operation(summary = "Update enabled user",
            description = "Update enabled user")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<String> enabled(
            @PathVariable Long id,
            @RequestBody UserRequestEnabledDto userRequestEnabledDto
    ) throws RestException {
        final Boolean enabled = userRequestEnabledDto.getEnabled();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.enabledUser(id, enabled));
    }

}
