package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.entity.UserEntity;
import bg.softuni.myownproject.model.entity.UserRoleEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.repository.UserRepository;
import bg.softuni.myownproject.user.ProjectUserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {

    private static final String TEST_USERNAME = "elsifeither";
    private static final String NOT_EXISTENT_USERNAME = "noone";

    private AppUserDetailsService toTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserFound() {

        UserEntity testUser = new UserEntity()
                .setUsername(TEST_USERNAME)
                .setEmail("elsi@abv.bg")
                .setFirstName("Ivo")
                .setLastName("Iliev")
                .setPassword("123123")
                .setConfirmPassword("123123")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.MODERATOR),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));

        when(mockUserRepository.findByUsername(TEST_USERNAME))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = toTest.loadUserByUsername(TEST_USERNAME);

        Assertions.assertInstanceOf(ProjectUserDetails.class, userDetails);

        ProjectUserDetails projectUserDetails = (ProjectUserDetails) userDetails;

        Assertions.assertEquals(TEST_USERNAME, userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName(), projectUserDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), projectUserDetails.getLastName());
        Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(),
                projectUserDetails.getFullName());

        var expectedRoles = testUser.getRoles().stream().map(UserRoleEntity::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_USERNAME)
        );
    }

}
