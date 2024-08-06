package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.UserRegistrationDTO;
import bg.softuni.myownproject.model.entity.UserEntity;
import bg.softuni.myownproject.model.entity.UserRoleEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.repository.UserRepository;
import bg.softuni.myownproject.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleRepository mockUserRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        userService = new UserService(mockModelMapper, mockPasswordEncoder, mockUserRepository, mockUserRoleRepository);
    }

    @Test
    void testRegisterUser_NewUser() {

        UserRegistrationDTO userDTO = new UserRegistrationDTO();
        userDTO.setUsername("newuser");
        userDTO.setEmail("newuser@example.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password123");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("newuser");
        userEntity.setEmail("newuser@example.com");
        userEntity.setPassword("encodedPassword");

        UserRoleEntity role = new UserRoleEntity();
        role.setRole(UserRoleEnum.USER);

        when(mockUserRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())).thenReturn(Optional.empty());
        when(mockUserRoleRepository.findByRole(UserRoleEnum.USER)).thenReturn(Optional.of(role));
        when(mockModelMapper.map(userDTO, UserEntity.class)).thenReturn(userEntity);
        when(mockPasswordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

        boolean result = userService.registerUser(userDTO);

        assertTrue(result);
        verify(mockUserRepository, times(1)).save(argThat(user ->
                user.getUsername().equals(userDTO.getUsername()) &&
                        user.getEmail().equals(userDTO.getEmail()) &&
                        user.getPassword().equals("encodedPassword")
        ));
    }

    @Test
    void testRegisterUser_ExistingUser() {
        // Arrange
        UserRegistrationDTO userDTO = new UserRegistrationDTO();
        userDTO.setUsername("existinguser");
        userDTO.setEmail("existinguser@example.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password123");

        when(mockUserRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail()))
                .thenReturn(Optional.of(new UserEntity()));

        boolean result = userService.registerUser(userDTO);

        assertFalse(result);
        verify(mockUserRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    void testChangeUserRole() {

        Long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setRoles(new ArrayList<>());

        UserRoleEntity newRole = new UserRoleEntity();
        newRole.setRole(UserRoleEnum.ADMIN);

        when(mockUserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mockUserRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(Optional.of(newRole));

        userService.changeUserRole(userId, UserRoleEnum.ADMIN);

        verify(mockUserRepository, times(1)).save(argThat(updatedUser ->
                updatedUser.getRoles().contains(newRole) &&
                        updatedUser.getRoles().size() == 1
        ));
    }

    @Test
    void testFindAll() {
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        List<UserEntity> users = List.of(user1, user2);

        when(mockUserRepository.findAll()).thenReturn(users);
        List<UserEntity> result = userService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }
}
