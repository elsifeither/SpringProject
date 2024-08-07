package bg.softuni.restaddition.service;

import bg.softuni.restaddition.model.entity.Facility;
import bg.softuni.restaddition.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> findById(Long id) {
        return facilityRepository.findById(id);
    }

    public Facility save(Facility facility) {
        return facilityRepository.save(facility);
    }

    public void deleteById(Long id) {
        facilityRepository.deleteById(id);
    }
}

