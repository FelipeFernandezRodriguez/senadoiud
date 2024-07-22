package co.edu.iudigital.senadoiud.services.impl;

import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieRequestDto;
import co.edu.iudigital.senadoiud.dtos.politicalPartie.PoliticalPartieResponseDto;
import co.edu.iudigital.senadoiud.exceptions.BadRequestException;
import co.edu.iudigital.senadoiud.exceptions.ErrorDto;
import co.edu.iudigital.senadoiud.exceptions.InternalServerErrorException;
import co.edu.iudigital.senadoiud.exceptions.NotFoundException;
import co.edu.iudigital.senadoiud.exceptions.RestException;
import co.edu.iudigital.senadoiud.models.PoliticalPartie;
import co.edu.iudigital.senadoiud.repositories.IPoliticalPartieRepository;
import co.edu.iudigital.senadoiud.services.ifaces.IPoliticalPartieService;
import co.edu.iudigital.senadoiud.utils.PoliticalPartieMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PoliticalPartieServiceImpl implements IPoliticalPartieService {

    @Autowired
    private IPoliticalPartieRepository politicalPartieRepository;

    @Autowired
    private PoliticalPartieMapper politicalPartieMapper;

    @Override
    public PoliticalPartieResponseDto createPoliticalPartie(PoliticalPartieRequestDto politicalPartieRequestDto) throws RestException {
        log.info("Create PoliticalPartie Service");
        PoliticalPartie politicalPartie = politicalPartieRepository.findByName(politicalPartieRequestDto.getName());

        if (politicalPartie != null) {
            throw new BadRequestException(
                    ErrorDto.builder()
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .message("Partido político ya existe")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .date(LocalDateTime.now())
                            .build());
        }

        try{
            politicalPartie = politicalPartieMapper.toPoliticalPartie(politicalPartieRequestDto);
            politicalPartie = politicalPartieRepository.save(politicalPartie);
            return politicalPartieMapper.toPoliticalPartieResponseDto(politicalPartie);
        }catch (Exception e){
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
    public PoliticalPartieResponseDto updatePoliticalPartie(Long id, PoliticalPartieRequestDto politicalPartieRequestDto)  throws RestException {
        log.info("Update PoliticalPartie Service");

        PoliticalPartie politicalPartieBd = politicalPartieRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorDto.builder()
                                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                        .message("Partido político no existe")
                                        .status(404)
                                        .date(LocalDateTime.now())
                                        .build())
                );

        PoliticalPartie politicalPartieName = politicalPartieRepository.findByName(politicalPartieRequestDto.getName());

        if (politicalPartieName != null) {
            throw new BadRequestException(
                    ErrorDto.builder()
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .message("Partido político ya existe")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .date(LocalDateTime.now())
                            .build());
        }

        try{
            politicalPartieBd.setName(politicalPartieRequestDto.getName() != null ? politicalPartieRequestDto.getName() : politicalPartieBd.getName());
            politicalPartieBd.setSlogan(politicalPartieRequestDto.getSlogan() != null ? politicalPartieRequestDto.getSlogan() : politicalPartieBd.getSlogan());
            politicalPartieBd.setImage(politicalPartieRequestDto.getImage() != null ? politicalPartieRequestDto.getImage() : politicalPartieBd.getImage());
            politicalPartieBd = politicalPartieRepository.save(politicalPartieBd);
            return politicalPartieMapper.toPoliticalPartieResponseDto(politicalPartieBd);
        }catch (Exception e){
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
    public void deletePoliticalPartie(Long id)  throws RestException {
        log.info("Delete PoliticalPartie Service");

        politicalPartieRepository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException(
                            ErrorDto.builder()
                                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                    .message("Partido político no existe")
                                    .status(404)
                                    .date(LocalDateTime.now())
                                    .build())
            );

        try{
            politicalPartieRepository.deleteById(id);
        }catch (Exception e){
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
    public PoliticalPartieResponseDto searchPoliticalPartieById(Long id)  throws RestException {
        log.info("Search PoliticalPartie by ID Service");

        PoliticalPartie politicalPartieBd = politicalPartieRepository.findById(id)
                            .orElseThrow(() ->
                                    new NotFoundException(
                                            ErrorDto.builder()
                                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                            .message("Partido político no existe")
                                            .status(404)
                                            .date(LocalDateTime.now())
                                            .build())
                            );

        try{
            return politicalPartieMapper.toPoliticalPartieResponseDto(politicalPartieBd);
        }catch (Exception e){
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
    public List<PoliticalPartieResponseDto> searchPoliticalParties()  throws RestException {
        log.info("Search all PoliticalParties Service");

        List<PoliticalPartie> politicalPartieList = politicalPartieRepository.findAll();
        return politicalPartieMapper.toPoliticalPartieDtoList(politicalPartieList);
    }

}
