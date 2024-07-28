package bg.softuni.myownproject.repository;

import bg.softuni.myownproject.model.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {

}
