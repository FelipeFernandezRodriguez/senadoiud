package co.edu.iudigital.senadoiud.services.ifaces;

import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteRequestDto;
import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import org.springframework.security.core.Authentication;

public interface IVoteService {

    VoteResponseDto voteProject(Long id, Authentication authentication, VoteRequestDto voteRequestDto)  throws RestException;

}
