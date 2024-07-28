package bg.softuni.myownproject.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddTeamDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    private Long coachId;

    public AddTeamDTO() {
    }

    public AddTeamDTO(String name, Long coachId) {
        this.name = name;
        this.coachId = coachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
}

