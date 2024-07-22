package co.edu.iudigital.senadoiud.utils;

import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteRequestDto;
import co.edu.iudigital.senadoiud.dtos.VotesDto.VoteResponseDto;
import co.edu.iudigital.senadoiud.dtos.users.UserRequestDto;
import co.edu.iudigital.senadoiud.dtos.users.UserResponseDto;
import co.edu.iudigital.senadoiud.models.UserEntity;
import co.edu.iudigital.senadoiud.models.Vote;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public Vote toVote(VoteRequestDto voteRequestDto){
        Vote vote = new Vote();
        vote.setVoteType(voteRequestDto.getVoteType());
        return vote;
    }

    public VoteResponseDto toVoteResponseDto(Vote vote){
        return VoteResponseDto.builder()
                .voteType(vote.getVoteType().getName())
                .build();
    }

}
