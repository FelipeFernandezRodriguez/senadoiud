package co.edu.iudigital.senadoiud.repositories;

import co.edu.iudigital.senadoiud.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoteRepository extends JpaRepository<Vote, Long> {



}
