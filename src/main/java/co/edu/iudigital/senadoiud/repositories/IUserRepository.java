package co.edu.iudigital.senadoiud.repositories;

import co.edu.iudigital.senadoiud.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);
}
