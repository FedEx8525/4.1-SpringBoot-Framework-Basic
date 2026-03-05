package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.exception.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        User user = new User();
        user.setEmail("test@mail.com");

        when(userRepository.existsByEmail("test@mail.com")).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(user);
        });
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_shouldSaveUserWhenEmailDoesNotExist() {
        User user = new User();
        user.setEmail("new@mail.com");

        when(userRepository.existsByEmail("new@mail.com")).thenReturn(false);

        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.createUser(user);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void listUsers_shouldReturnAll_whenNameIsNull() {

        when(userRepository.findAll()).thenReturn(List.of(new User()));

       userService.listUsers(null);

        verify(userRepository, times(1)).findAll();
        verify(userRepository, never()).searchByName(anyString());
    }

    @Test
    void listUsers_shouldSearchByName_whenNameIsProvided() {

        String name = "Ana";
        when(userRepository.searchByName(name)).thenReturn(List.of(new User()));

        userService.listUsers(name);

        verify(userRepository, times(1)).searchByName(name);
        verify(userRepository, never()).findAll();
    }


}
