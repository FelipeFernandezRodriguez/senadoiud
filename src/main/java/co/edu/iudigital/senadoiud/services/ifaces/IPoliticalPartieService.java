package co.edu.iudigital.senadoiud.services.ifaces;

import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieRequestDto;
import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieResponseDto;
import co.edu.iudigital.senadoiud.exceptions.RestException;

import java.util.List;

public interface IPoliticalPartieService {

    PoliticalPartieResponseDto createPoliticalPartie(PoliticalPartieRequestDto politicalPartie) throws RestException;

    PoliticalPartieResponseDto updatePoliticalPartie(Long id, PoliticalPartieRequestDto politicalPartieRequestDto) throws RestException;

    void deletePoliticalPartie(Long id) throws RestException;

    PoliticalPartieResponseDto searchPoliticalPartieById(Long id) throws RestException;

    List<PoliticalPartieResponseDto> searchPoliticalParties() throws RestException;

}
