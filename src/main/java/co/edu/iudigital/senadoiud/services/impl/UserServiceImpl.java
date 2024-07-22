package co.edu.iudigital.senadoiud.services.impl;

import co.edu.iudigital.senadoiud.dtos.users.UserRequestDto;
import co.edu.iudigital.senadoiud.dtos.users.UserRequestUpdateDto;
import co.edu.iudigital.senadoiud.dtos.users.UserResponseDto;
import co.edu.iudigital.senadoiud.exceptions.BadRequestException;
import co.edu.iudigital.senadoiud.exceptions.ErrorDto;
import co.edu.iudigital.senadoiud.exceptions.InternalServerErrorException;
import co.edu.iudigital.senadoiud.exceptions.NotFoundException;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.models.UserEntity;
import co.edu.iudigital.senadoiud.repositories.IUserRepository;
import co.edu.iudigital.senadoiud.services.ifaces.IEmailService;
import co.edu.iudigital.senadoiud.services.ifaces.IUserService;
import co.edu.iudigital.senadoiud.utils.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IEmailService emailService;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws RestException {

        UserEntity userEntity = userRepository.findByEmail(userRequestDto.getEmail());

        if (userEntity != null) {
            throw new BadRequestException(
                    ErrorDto.builder()
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .message("Email ya existe")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .date(LocalDateTime.now())
                            .build());
        }

        try {
            userEntity = userMapper.toUser(userRequestDto);
            userEntity.setEnabled(true);
            userEntity = userRepository.save(userEntity);

            if (emailService.sendMail(
                    "Hola " + userEntity.getName() + " Te has dado de alta en Senado IUD con el userEntity " + userEntity.getEmail(),
                    userEntity.getEmail(),
                    "Registro exitoso en Senado IUD"
            )) {
                log.info("Mensaje enviado");
            } else {
                log.warn("Mensaje no enviado");
            }
        return userMapper.toUserResponseDto(userEntity);
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
    public UserResponseDto updateUser(UserRequestUpdateDto userRequestUpdateDTO, Authentication authentication) throws RestException {

        UserEntity userEntity = userRepository.findByEmail(authentication.getName());

        if (userEntity == null) {
            throw new BadRequestException(
                    ErrorDto.builder()
                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .message("Usuario NO existe")
                            .status(HttpStatus.NOT_FOUND.value())
                            .date(LocalDateTime.now())
                            .build());
        }

        userEntity.setName(userRequestUpdateDTO.getName() != null ? userRequestUpdateDTO.getName() : userEntity.getName());
        userEntity.setLastName(userRequestUpdateDTO.getLastName() != null ? userRequestUpdateDTO.getLastName() : userEntity.getLastName());
        userEntity.setPassword(userRequestUpdateDTO.getPassword() != null ? userRequestUpdateDTO.getPassword() : userEntity.getPassword());

        try {
            userEntity = userRepository.save(userEntity);
            return userMapper.toUserResponseDto(userEntity);
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
    public String enabledUser(final Long id, final Boolean enabled) throws RestException {
        log.info("Enabled User Service");

        UserEntity userEnabled = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorDto.builder()
                                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                                        .message("Usuario NO existe")
                                        .status(HttpStatus.NOT_FOUND.value())
                                        .date(LocalDateTime.now())
                                        .build())
                        );

        try{
            userEnabled.setEnabled(enabled);
            userEnabled = userRepository.saveAndFlush(userEnabled);
            return enabled ? "Enabled User Success" : "Disabled User Success";
        }catch (Exception e){
            throw new InternalServerErrorException(
                    ErrorDto.builder()
                            .error("Error General")
                            .status(500)
                            .message("Error al intentar actualizar")
                            .date(LocalDateTime.now())
                            .build()
            );
        }
    }

    @Override
    public List<UserResponseDto> searchUsers() throws RestException {
        return Collections.emptyList();
    }

    @Override
    public UserResponseDto searchUserById(Long id) throws RestException {
        return null;
    }

    @Override
    public UserEntity searchByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResponseDto searchUserByEmail(Authentication authentication) throws RestException {

        if(!authentication.isAuthenticated()) {
            throw new BadRequestException(
                    ErrorDto.builder()
                            .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                            .message("Usuario NO autenticado")
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .date(LocalDateTime.now())
                            .build());
        }

        UserEntity userEntity = userRepository.findByEmail(authentication.getName());

        if(userEntity == null) {
            throw new BadRequestException(
                    ErrorDto.builder()
                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .message("UserEntity NO existe")
                            .status(HttpStatus.NOT_FOUND.value())
                            .date(LocalDateTime.now())
                            .build());
        }

        return userMapper.toUserResponseDto(userEntity);

    }

    @Override
    public UserResponseDto uploadImage(MultipartFile image, Authentication authentication) throws RestException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity == null) {
            log.error("Usuario no existe: " + username);
            throw new UsernameNotFoundException("Usuario no existe: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userEntity.getRole().getName());
        authorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getEnabled(),
                true,
                true,
                true,
                authorities);

    }
}
