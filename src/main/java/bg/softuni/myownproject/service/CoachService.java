package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddCoachDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.repository.CoachRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final ModelMapper modelMapper;
    private final CoachRepository coachRepository;

    public CoachService(ModelMapper modelMapper, CoachRepository coachRepository) {
        this.modelMapper = modelMapper;
        this.coachRepository = coachRepository;
    }

    public void addCoach(AddCoachDTO addCoachDTO) {
        Coach mappedCoach = modelMapper.map(addCoachDTO, Coach.class);
        coachRepository.save(mappedCoach);
    }

    public List<Coach> findAllCoaches() {
        return coachRepository.findAll();
    }


    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }
}
