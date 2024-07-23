package bg.softuni.myownproject.repository;

import aj.org.objectweb.asm.commons.Remapper;
import bg.softuni.myownproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
