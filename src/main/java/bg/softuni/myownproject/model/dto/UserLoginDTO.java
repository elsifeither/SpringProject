package bg.softuni.myownproject.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


