package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.entity.Facility;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FacilityService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081/facilities";

    public FacilityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Facility> getAllFacilities() {
        Facility[] facilities = restTemplate.getForObject(baseUrl, Facility[].class);
        assert facilities != null;
        return Arrays.asList(facilities);
    }

    public Facility getFacilityById(Long id) {
        return restTemplate.getForObject(baseUrl + "/" + id, Facility.class);
    }

    public Facility createFacility(Facility facility) {
        return restTemplate.postForObject(baseUrl, facility, Facility.class);
    }

    public void deleteFacility(Long id) {
        restTemplate.delete(baseUrl + "/" + id);
    }
}