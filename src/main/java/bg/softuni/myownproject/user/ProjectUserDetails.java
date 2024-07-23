//package bg.softuni.myownproject.user;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//
//public class ProjectUserDetails extends User {
//
//  private final String firstName;
//  private final String lastName;
//
//  public ProjectUserDetails(
//      String username,
//      String password,
//      String firstName,
//      String lastName
//  ) {
//    super(username, password, authorities);
//    this.firstName = firstName;
//    this.lastName = lastName;
//  }
//
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public String getFullName() {
//    StringBuilder fullName = new StringBuilder();
//    if (firstName != null) {
//      fullName.append(firstName);
//    }
//    if (lastName != null) {
//      if (!fullName.isEmpty()) {
//        fullName.append(" ");
//      }
//      fullName.append(lastName);
//    }
//
//    return fullName.toString();
//  }
//}
