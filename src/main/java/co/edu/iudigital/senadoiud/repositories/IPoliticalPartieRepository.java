package co.edu.iudigital.senadoiud.repositories;

import co.edu.iudigital.senadoiud.models.PoliticalPartie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPoliticalPartieRepository extends JpaRepository<PoliticalPartie, Long> {

    PoliticalPartie findByName(String name);

}
