package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddCoachDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.repository.CoachRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoachServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private CoachService coachService;

    private AddCoachDTO addCoachDTO;
    private Coach coach;

    @BeforeEach
    void setUp() {
        addCoachDTO = new AddCoachDTO();
        coach = new Coach();
    }

    @Test
    void testAddCoach() {

        when(modelMapper.map(addCoachDTO, Coach.class)).thenReturn(coach);
        coachService.addCoach(addCoachDTO);

        verify(modelMapper, times(1)).map(addCoachDTO, Coach.class);
        verify(coachRepository, times(1)).save(coach);
    }

    @Test
    void testFindAllCoaches() {
        List<Coach> expectedCoaches = List.of(new Coach(), new Coach());
        when(coachRepository.findAll()).thenReturn(expectedCoaches);

        List<Coach> actualCoaches = coachService.findAllCoaches();

        assertEquals(expectedCoaches, actualCoaches);
        verify(coachRepository, times(1)).findAll();
    }

    @Test
    void testFindAll() {

        List<Coach> expectedCoaches = List.of(new Coach(), new Coach());
        when(coachRepository.findAll()).thenReturn(expectedCoaches);

        List<Coach> actualCoaches = coachService.findAll();

        assertEquals(expectedCoaches, actualCoaches);
        verify(coachRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {

        Long id = 1L;
        Optional<Coach> expectedCoach = Optional.of(new Coach());
        when(coachRepository.findById(id)).thenReturn(expectedCoach);

        Optional<Coach> actualCoach = coachService.findById(id);

        assertTrue(actualCoach.isPresent());
        assertEquals(expectedCoach, actualCoach);
        verify(coachRepository, times(1)).findById(id);
    }
}
