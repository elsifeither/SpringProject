package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.entity.User;
import bg.softuni.myownproject.repository.UserRepository;
//
//import bg.softuni.myownproject.user.ProjectUserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class ProjectUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public ProjectUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//
//        return userRepository
//                .findByEmail(email)
//                .map(ProjectUserDetailsService::map)
//                .orElseThrow(
//                        () -> new UsernameNotFoundException("User with email " + email + " not found!"));
//    }
//
//
//
//    private static UserDetails map(User userEntity) {
//
//        return new ProjectUserDetails(
//                userEntity.getUsername(),
//                userEntity.getEmail(),
//                userEntity.getPassword(),
//                userEntity.getConfirmPassword()
//        );
//    }
//}
