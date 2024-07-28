package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddTrainingDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.model.entity.Team;
import bg.softuni.myownproject.model.entity.TrainingSession;
import bg.softuni.myownproject.repository.TrainingSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final TeamService teamService;
    private final CoachService coachService;

    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository, TeamService teamService, CoachService coachService) {
        this.trainingSessionRepository = trainingSessionRepository;
        this.teamService = teamService;
        this.coachService = coachService;
    }

    public List<TrainingSession> findAll() {
        return trainingSessionRepository.findAll();
    }

    public Optional<TrainingSession> findById(Long id) {
        return trainingSessionRepository.findById(id);
    }

    public void save(TrainingSession trainingSession) {
        trainingSessionRepository.save(trainingSession);
    }

    public void deleteById(Long id) {
        trainingSessionRepository.deleteById(id);
    }


    public void addTrainingSession(AddTrainingDTO trainingSessionDTO) {
        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setDate(trainingSessionDTO.getDateAsInstant());
        trainingSession.setLocation(trainingSessionDTO.getLocation());
        Optional<Team> team = teamService.findById(trainingSessionDTO.getTeam().getId());
        Optional<Coach> coach = coachService.findById(trainingSessionDTO.getCoach().getId());
        if (team.isEmpty()) {
            throw new IllegalArgumentException("Invalid team ID: " + trainingSessionDTO.getTeam().getId());
        }
        if (coach.isEmpty()) {
            throw new IllegalArgumentException("Invalid coach ID: " + trainingSessionDTO.getCoach().getId());
        }
        trainingSession.setTeam(team.get());
        trainingSession.setCoach(coach.get());
        trainingSessionRepository.save(trainingSession);
    }
}