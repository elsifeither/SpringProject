package bg.softuni.myownproject.model.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
