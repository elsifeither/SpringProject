package bg.softuni.restaddition.web;

import bg.softuni.restaddition.model.entity.Facility;
import bg.softuni.restaddition.repository.FacilityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    private final FacilityRepository facilityRepository;

    public FacilityController(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Facility getFacilityById(@PathVariable Long id) {
        return facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + id));
    }

    @PostMapping
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityRepository.save(facility);
    }

    @DeleteMapping("/{id}")
    public void deleteFacility(@PathVariable Long id) {
        facilityRepository.deleteById(id);
    }


}