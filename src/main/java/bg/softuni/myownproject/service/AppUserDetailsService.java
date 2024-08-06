package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.entity.UserEntity;
import bg.softuni.myownproject.repository.UserRepository;

import bg.softuni.myownproject.user.ProjectUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with  " + username + " not found!"));
    }



    private UserDetails map(UserEntity userEntity) {
        return new ProjectUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                mapToGrantedAuthorities(userEntity),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(UserEntity userEntity) {
        return userEntity.
                getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toUnmodifiableList());
    }
}
