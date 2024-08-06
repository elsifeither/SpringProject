package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddPlayerDTO;
import bg.softuni.myownproject.model.entity.Player;
import bg.softuni.myownproject.model.enums.GenderEnum;
import bg.softuni.myownproject.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    private PlayerService toTest;

    @Mock
    private PlayerRepository mockPlayerRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        toTest = new PlayerService(mockModelMapper, mockPlayerRepository);
    }

    @Test
    void testEnroll() {
        AddPlayerDTO addPlayerDTO = new AddPlayerDTO();
        addPlayerDTO.setFirstName("John");
        addPlayerDTO.setLastName("Doe");
        addPlayerDTO.setBirthDate(LocalDate.of(1990, 1, 1));
        addPlayerDTO.setGender(GenderEnum.MALE);
        addPlayerDTO.setTeamId(1L);

        Player player = new Player();
        player.setFirstName("John");
        player.setLastName("Doe");
        player.setBirthDate(LocalDate.of(1990, 1, 1));
        player.setGender(GenderEnum.MALE);

        when(mockModelMapper.map(addPlayerDTO, Player.class)).thenReturn(player);

        toTest.enroll(addPlayerDTO);

        verify(mockPlayerRepository, times(1)).save(player);
    }

    @Test
    void testDeletePlayer() {
        Long playerId = 1L;

        toTest.deletePlayer(playerId);
        verify(mockPlayerRepository, times(1)).deleteById(playerId);
    }
}
