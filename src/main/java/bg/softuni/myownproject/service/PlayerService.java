package bg.softuni.myownproject.service;


import bg.softuni.myownproject.model.dto.AddPlayerDTO;
import bg.softuni.myownproject.model.entity.Player;
import bg.softuni.myownproject.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final ModelMapper modelMapper;
    private final PlayerRepository playerRepository;

    public PlayerService(ModelMapper modelMapper, PlayerRepository playerRepository) {
        this.modelMapper = modelMapper;
        this.playerRepository = playerRepository;
    }

    public void enroll(AddPlayerDTO addPlayerDTO) {
        Player mappedPlayer = modelMapper.map(addPlayerDTO, Player.class);
        playerRepository.save(mappedPlayer);
    }

    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
