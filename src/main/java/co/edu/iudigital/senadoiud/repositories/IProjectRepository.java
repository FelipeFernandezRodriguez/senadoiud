package co.edu.iudigital.senadoiud.repositories;

import co.edu.iudigital.senadoiud.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {



}
