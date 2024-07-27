package bg.softuni.myownproject.init;


import bg.softuni.myownproject.model.entity.UserRoleEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitRoles implements CommandLineRunner {


    private final UserRoleRepository userRoleRepository;

    public InitRoles(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        long count = userRoleRepository.count();

        if (count > 0) {
            return;
        }


        List<UserRoleEntity> roles = Arrays.asList(
                new UserRoleEntity(1L, UserRoleEnum.USER),
                new UserRoleEntity(2L, UserRoleEnum.MODERATOR),
                new UserRoleEntity(3L, UserRoleEnum.ADMIN)
        );

        userRoleRepository.saveAllAndFlush(roles);

    }
}