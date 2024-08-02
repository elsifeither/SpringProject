package bg.softuni.myownproject.repository;

import bg.softuni.myownproject.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Override
    List<Team> findAll();

    Optional<Team> findByName(String name);
}
