package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
    }

    @Test
    void save_shouldAddUserToList() {
        User user = new User();
        user.setName("Ana");
        user.setEmail("ana@mail.com");

        User savedUser = repository.save(user);

        assertNotNull(savedUser, "The saved user should not be null");
        assertNotNull(savedUser.getId(), "The user id should not be null");

        assertEquals(1, repository.findAll().size(), "The list should have exactly 1 user");
    }

    @Test
    void findAll_shouldReturnUsersList() {
        User u1 = new User();
        User u2 = new User();
        u1.setName("Ana");
        u1.setEmail("ana@mail.com");
        u2.setName("Marc");
        u2.setEmail("marc@mail.com");

        repository.save(u1);
        repository.save(u2);

        List<User> result = repository.findAll();

        assertEquals(2, result.size(), "The list should have 2 users");
        assertTrue(result.contains(u1), "The list should contains Ana");
        assertTrue(result.contains(u2), "The list should contains Marc");

    }
}

