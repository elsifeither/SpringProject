package bg.softuni.myownproject.web;

import bg.softuni.myownproject.model.entity.Facility;
import bg.softuni.myownproject.service.FacilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public Facility getFacilityById(@PathVariable Long id) {
        return facilityService.getFacilityById(id);
    }

    @PostMapping
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityService.createFacility(facility);
    }

    @DeleteMapping("/{id}")
    public void deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
    }
}