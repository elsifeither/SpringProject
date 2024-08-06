package bg.softuni.myownproject.init;

import bg.softuni.myownproject.model.entity.UserRoleEntity;
import bg.softuni.myownproject.model.enums.UserRoleEnum;
import bg.softuni.myownproject.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InitRolesTest {

    @InjectMocks
    private InitRoles initRoles;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Captor
    private ArgumentCaptor<List<UserRoleEntity>> userRoleArgumentCaptor;

    @Test
    void testRunWhenRolesAreNotInitialized() throws Exception {

        when(userRoleRepository.count()).thenReturn(0L);
        initRoles.run();

        verify(userRoleRepository, times(1)).saveAllAndFlush(userRoleArgumentCaptor.capture());
        List<UserRoleEntity> savedRoles = userRoleArgumentCaptor.getValue();

        assertEquals(3, savedRoles.size());
        assertEquals(UserRoleEnum.USER, savedRoles.get(0).getRole());
        assertEquals(UserRoleEnum.MODERATOR, savedRoles.get(1).getRole());
        assertEquals(UserRoleEnum.ADMIN, savedRoles.get(2).getRole());
    }

    @Test
    void testRunWhenRolesAreAlreadyInitialized() throws Exception {

        when(userRoleRepository.count()).thenReturn(1L);
        initRoles.run();
        verify(userRoleRepository, times(0)).saveAllAndFlush((List<UserRoleEntity>) ArgumentCaptor.forClass(List.class).capture());
    }
}
