package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddTeamDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.model.entity.Team;
import bg.softuni.myownproject.repository.CoachRepository;
import bg.softuni.myownproject.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;


    public TeamService(TeamRepository teamRepository, CoachRepository coachRepository) {
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public void saveTeam(AddTeamDTO teamDTO) {

        if (teamRepository.findByName(teamDTO.getName()).isPresent()) {
            return;
        }
        Team team = new Team();
        team.setName(teamDTO.getName());

        if (teamDTO.getCoachId() != null) {
            Coach coach = coachRepository.findById(teamDTO.getCoachId()).orElse(null);
            team.setCoach(coach);
        }

        teamRepository.save(team);
    }

    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }
}
