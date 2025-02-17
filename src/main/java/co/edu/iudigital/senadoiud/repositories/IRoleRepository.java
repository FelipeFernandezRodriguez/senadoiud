package co.edu.iudigital.senadoiud.repositories;

import co.edu.iudigital.senadoiud.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
