package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddTeamDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.model.entity.Team;
import bg.softuni.myownproject.repository.CoachRepository;
import bg.softuni.myownproject.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    private TeamService toTestTeamService;

    @Mock
    private TeamRepository mockTeamRepository;

    @Mock
    private CoachRepository mockCoachRepository;

    @BeforeEach
    void setUp() {
        toTestTeamService = new TeamService(mockTeamRepository, mockCoachRepository);
    }

    @Test
    void testFindAll() {

        Team team1 = new Team();
        team1.setName("Team A");
        Team team2 = new Team();
        team2.setName("Team B");
        List<Team> teams = List.of(team1, team2);

        when(mockTeamRepository.findAll()).thenReturn(teams);
        List<Team> result = toTestTeamService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("Team B", result.get(1).getName());
    }

    @Test
    void testSaveTeam_NewTeam() {

        AddTeamDTO teamDTO = new AddTeamDTO("Team C", null);
        Team team = new Team();
        team.setName("Team C");

        when(mockTeamRepository.findByName("Team C")).thenReturn(Optional.empty());
        toTestTeamService.saveTeam(teamDTO);
        verify(mockTeamRepository, times(1)).save(argThat(t ->
                "Team C".equals(t.getName()) &&
                        t.getCoach() == null
        ));

    }

    @Test
    void testSaveTeam_ExistingTeam() {
        AddTeamDTO teamDTO = new AddTeamDTO("Team D", null);
        when(mockTeamRepository.findByName("Team D")).thenReturn(Optional.of(new Team()));
        toTestTeamService.saveTeam(teamDTO);
        verify(mockTeamRepository, times(0)).save(any(Team.class));
    }

    @Test
    void testSaveTeam_WithCoach() {
        Long coachId = 1L;
        Coach coach = new Coach();
        AddTeamDTO teamDTO = new AddTeamDTO("Team E", coachId);
        Team team = new Team();
        team.setName("Team E");
        when(mockTeamRepository.findByName("Team E")).thenReturn(Optional.empty());
        when(mockCoachRepository.findById(coachId)).thenReturn(Optional.of(coach));
        toTestTeamService.saveTeam(teamDTO);
        verify(mockTeamRepository, times(1)).save(argThat(t ->
                "Team E".equals(t.getName()) &&
                        coach.equals(t.getCoach())
        ));
    }

    @Test
    void testFindById() {
        Long teamId = 1L;
        Team team = new Team();
        team.setId(teamId);
        when(mockTeamRepository.findById(teamId)).thenReturn(Optional.of(team));
        Optional<Team> result = toTestTeamService.findById(teamId);
        assertTrue(result.isPresent());
        assertEquals(teamId, result.get().getId());
    }
}
