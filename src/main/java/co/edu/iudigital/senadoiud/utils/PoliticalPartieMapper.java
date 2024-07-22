package co.edu.iudigital.senadoiud.utils;

import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieRequestDto;
import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieResponseDto;
import co.edu.iudigital.senadoiud.models.PoliticalPartie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PoliticalPartieMapper {

    public PoliticalPartie toPoliticalPartie(PoliticalPartieRequestDto politicalPartieRequestDto) {
        PoliticalPartie politicalPartie = new PoliticalPartie();
        politicalPartie.setName(politicalPartieRequestDto.getName());
        politicalPartie.setSlogan(politicalPartieRequestDto.getSlogan());
        politicalPartie.setImage(politicalPartieRequestDto.getImage());
        return politicalPartie;
    }

    public PoliticalPartieResponseDto toPoliticalPartieResponseDto(PoliticalPartie politicalPartie) {
        return PoliticalPartieResponseDto.builder()
                .name(politicalPartie.getName())
                .slogan(politicalPartie.getSlogan())
                .image(politicalPartie.getImage())
                .build();
    }

    public List<PoliticalPartieResponseDto> toPoliticalPartieDtoList(List<PoliticalPartie> politicalPartieList){
        return politicalPartieList.stream()
                .map(politicalPartie -> toPoliticalPartieResponseDto(politicalPartie))
                .collect(Collectors.toList());
    }
}
