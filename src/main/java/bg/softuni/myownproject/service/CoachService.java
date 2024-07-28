package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.AddCoachDTO;
import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.repository.CoachRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
