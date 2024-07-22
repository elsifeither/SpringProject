package bg.softuni.myownproject.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String experience;

    @OneToMany(mappedBy = "coach")
    private List<Team> teams;
}
