package bg.softuni.myownproject.model.entity;


import bg.softuni.myownproject.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity() {

    }

    public UserRoleEntity(Long id,
                          UserRoleEnum role) {
        this.id = id;
        this.role = role;

    }


    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }




}
