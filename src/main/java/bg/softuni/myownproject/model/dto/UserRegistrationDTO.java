package bg.softuni.myownproject.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {

  @NotEmpty
  @Size(min = 5, max = 20)
  private String username;

  @NotEmpty
  @Email
  @Size(min = 5, max = 20)
  private String email;

  @NotEmpty
  @Size(min = 2, max = 20)
  private String firstName;

  @NotEmpty
  @Size(min = 2, max = 20)
  private String lastName;

  @NotEmpty
  @Size(min = 5, max = 20)
  private String password;

  @NotEmpty
  @Size(min = 5, max = 20)
  private String confirmPassword;



  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getFirstName() {
    return firstName;
  }

  public UserRegistrationDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  @Override
  public String toString() {
    return "UserRegistrationDTO{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", password='" + password + '\'' +
            ", confirmPassword='" + confirmPassword + '\'' +
            '}';
  }
}
