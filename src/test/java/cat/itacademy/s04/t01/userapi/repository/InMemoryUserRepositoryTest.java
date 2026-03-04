package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}

