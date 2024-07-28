package bg.softuni.myownproject.service;

import bg.softuni.myownproject.model.dto.UserRegistrationDTO;
import bg.softuni.myownproject.model.entity.UserEntity;
import bg.softuni.myownproject.model.entity.UserRoleEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.repository.UserRepository;
import bg.softuni.myownproject.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    public UserService(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }


    public boolean registerUser(UserRegistrationDTO userRegistration) {

        Optional<UserEntity> existingUser = userRepository
                .findByUsernameOrEmail(userRegistration.getUsername(), userRegistration.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        UserEntity user = map(userRegistration);
        user.setRoles(List.of(userRoleRepository.findByRole(UserRoleEnum.USER).orElseThrow()));
        userRepository.save(user);
        return true;
    }


    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        mappedEntity.setConfirmPassword(passwordEncoder.encode(userRegistrationDTO.getConfirmPassword()));
        return mappedEntity;
    }

    public void changeUserRole(Long userId, UserRoleEnum newRole) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserRoleEntity role = userRoleRepository.findByRole(newRole)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<UserRoleEntity> mutableRoles = new ArrayList<>(user.getRoles());
        mutableRoles.clear();
        mutableRoles.add(role);
        user.setRoles(mutableRoles);

        userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
