package bg.softuni.myownproject.model.dto;

import bg.softuni.myownproject.model.entity.Coach;
import bg.softuni.myownproject.model.entity.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class AddTrainingDTO {

    @NotNull
    private String date; // Use String to receive the date from the form

    @NotBlank
    @Size(min = 2, max = 20)
    private String location;

    @NotNull
    private Team team;

    @NotNull
    private Coach coach;

    public Instant getDateAsInstant() {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toInstant(ZoneOffset.UTC);
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
