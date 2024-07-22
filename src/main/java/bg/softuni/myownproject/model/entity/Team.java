package bg.softuni.myownproject.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

}
