package bg.softuni.myownproject.repository;

import bg.softuni.myownproject.model.entity.UserRoleEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRoleEnum userRoleEnum);
}
