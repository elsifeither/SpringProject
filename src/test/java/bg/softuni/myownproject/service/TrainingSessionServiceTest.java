package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddTrainingDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.model.entity.Team;
import bg.softuni.myownproject.model.entity.TrainingSession;
import bg.softuni.myownproject.repository.TrainingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingSessionServiceTest {

    private TrainingSessionService trainingSessionService;

    @Mock
    private TrainingSessionRepository mockTrainingSessionRepository;

    @Mock
    private TeamService mockTeamService;

    @Mock
    private CoachService mockCoachService;

    @BeforeEach
    void setUp() {
        trainingSessionService = new TrainingSessionService(mockTrainingSessionRepository, mockTeamService, mockCoachService);
    }

    @Test
    void testFindAll() {

        TrainingSession session1 = new TrainingSession();
        TrainingSession session2 = new TrainingSession();
        List<TrainingSession> sessions = List.of(session1, session2);

        when(mockTrainingSessionRepository.findAll()).thenReturn(sessions);
        List<TrainingSession> result = trainingSessionService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {

        Long sessionId = 1L;
        TrainingSession session = new TrainingSession();
        session.setId(sessionId);

        when(mockTrainingSessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        Optional<TrainingSession> result = trainingSessionService.findById(sessionId);

        assertTrue(result.isPresent());
        assertEquals(sessionId, result.get().getId());
    }

    @Test
    void testSave() {

        TrainingSession session = new TrainingSession();
        trainingSessionService.save(session);
        verify(mockTrainingSessionRepository, times(1)).save(session);
    }

    @Test
    void testDeleteById() {

        Long sessionId = 1L;
        trainingSessionService.deleteById(sessionId);
        verify(mockTrainingSessionRepository, times(1)).deleteById(sessionId);
    }

    @Test
    void testAddTrainingSession_ValidData() {

        Long teamId = 1L;
        Long coachId = 2L;
        Team team = new Team();
        team.setId(teamId);
        Coach coach = new Coach();
        coach.setId(coachId);

        String dateString = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        AddTrainingDTO trainingDTO = new AddTrainingDTO();
        trainingDTO.setDate(dateString);
        trainingDTO.setLocation("Location");
        trainingDTO.setTeam(team);
        trainingDTO.setCoach(coach);

        TrainingSession expectedSession = new TrainingSession();
        expectedSession.setDate(trainingDTO.getDateAsInstant());
        expectedSession.setLocation(trainingDTO.getLocation());
        expectedSession.setTeam(team);
        expectedSession.setCoach(coach);

        when(mockTeamService.findById(teamId)).thenReturn(Optional.of(team));
        when(mockCoachService.findById(coachId)).thenReturn(Optional.of(coach));

        trainingSessionService.addTrainingSession(trainingDTO);

        verify(mockTrainingSessionRepository, times(1)).save(argThat(trainingSession ->
                trainingSession.getDate().equals(expectedSession.getDate()) &&
                        trainingSession.getLocation().equals(expectedSession.getLocation()) &&
                        trainingSession.getTeam().equals(expectedSession.getTeam()) &&
                        trainingSession.getCoach().equals(expectedSession.getCoach())
        ));
    }

    @Test
    void testAddTrainingSession_InvalidTeam() {

        Long invalidTeamId = 1L;
        Long coachId = 2L;
        String dateString = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        AddTrainingDTO trainingDTO = new AddTrainingDTO();
        trainingDTO.setDate(dateString);
        trainingDTO.setLocation("Location");
        trainingDTO.setTeam(new Team(){{ setId(invalidTeamId); }});
        trainingDTO.setCoach(new Coach(){{ setId(coachId); }});

        when(mockTeamService.findById(invalidTeamId)).thenReturn(Optional.empty());
        when(mockCoachService.findById(coachId)).thenReturn(Optional.of(new Coach()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            trainingSessionService.addTrainingSession(trainingDTO);
        });
        assertEquals("Invalid team ID: 1", thrown.getMessage());
    }

    @Test
    void testAddTrainingSession_InvalidCoach() {

        Long teamId = 1L;
        Long invalidCoachId = 2L;
        String dateString = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        AddTrainingDTO trainingDTO = new AddTrainingDTO();
        trainingDTO.setDate(dateString);
        trainingDTO.setLocation("Location");
        trainingDTO.setTeam(new Team(){{ setId(teamId); }});
        trainingDTO.setCoach(new Coach(){{ setId(invalidCoachId); }});

        when(mockTeamService.findById(teamId)).thenReturn(Optional.of(new Team()));
        when(mockCoachService.findById(invalidCoachId)).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            trainingSessionService.addTrainingSession(trainingDTO);
        });
        assertEquals("Invalid coach ID: 2", thrown.getMessage());
    }
}
