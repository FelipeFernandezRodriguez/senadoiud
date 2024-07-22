package co.edu.iudigital.senadoiud.services.impl;

import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteRequestDto;
import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteResponseDto;
import co.edu.iudigital.senadoiud.exceptions.ErrorDto;
import co.edu.iudigital.senadoiud.exceptions.InternalServerErrorException;
import co.edu.iudigital.senadoiud.exceptions.NotFoundException;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.models.Project;
import co.edu.iudigital.senadoiud.models.UserEntity;
import co.edu.iudigital.senadoiud.models.Vote;
import co.edu.iudigital.senadoiud.repositories.IProjectRepository;
import co.edu.iudigital.senadoiud.repositories.IUserRepository;
import co.edu.iudigital.senadoiud.repositories.IVoteRepository;
import co.edu.iudigital.senadoiud.services.ifaces.IVoteService;
import co.edu.iudigital.senadoiud.utils.VoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IVoteRepository voteRepository;

    @Autowired
    private VoteMapper voteMapper;

    @Override
    public VoteResponseDto voteProject(Long id, Authentication authentication, VoteRequestDto voteRequestDto) throws RestException {

        log.info("Vote Project Request");

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

        try{
            Vote vote = voteMapper.toVote(voteRequestDto);
            vote.setUser(user);
            vote.setProject(project);
            vote = voteRepository.save(vote);
            return voteMapper.toVoteResponseDto(vote);
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

}
