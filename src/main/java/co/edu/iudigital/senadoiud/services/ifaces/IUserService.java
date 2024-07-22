package co.edu.iudigital.senadoiud.services.ifaces;

import co.edu.iudigital.senadoiud.dtos.users.UserRequestDto;
import co.edu.iudigital.senadoiud.dtos.users.UserRequestUpdateDto;
import co.edu.iudigital.senadoiud.dtos.users.UserResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.models.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {

    UserResponseDto createUser(UserRequestDto userRequestDto)  throws RestException;

    UserResponseDto updateUser(UserRequestUpdateDto userRequestUpdateDTO, Authentication authentication) throws RestException;

    String enabledUser(Long id, Boolean enabled) throws RestException;

    List<UserResponseDto> searchUsers() throws RestException;

    UserResponseDto searchUserById(Long id)  throws RestException;

    UserEntity searchByEmail(String email);

    UserResponseDto searchUserByEmail(Authentication authentication) throws RestException;

    UserResponseDto uploadImage(MultipartFile image, Authentication authentication) throws RestException;
}
