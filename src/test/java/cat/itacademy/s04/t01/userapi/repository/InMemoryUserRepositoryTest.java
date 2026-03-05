package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        User user = new User();
        user.setName("Paul");
        user.setEmail("paul@mail.com");
        User savedUser = repository.save(user);
        UUID id = savedUser.getId();

        Optional<User> result = repository.findById(id);

        assertTrue(result.isPresent(), "The Optional should have 1 user");
        assertEquals(id, result.get().getId());

    }

    @Test
    void findById_shouldReturnEmpty_whenIdDoesNotExist() {
        UUID randomId = UUID.randomUUID();

        Optional<User> result = repository.findById(randomId);

        assertTrue(result.isEmpty(), "The Optional should be empty for a not-existent id");
    }

    @Test
    void searchByName_shouldReturnUser_whenNameMatchesCaseInsensitive() {
        User user = new User();
        user.setName("Ana");
        user.setEmail("ana@mail.com");
        repository.save(user);

        List<User> result = repository.searchByName("ana");

        assertEquals(1, result.size(), "The list should have 1 user");
        assertEquals("Ana", result.getFirst().getName(), "The user name should be Ana");

    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {

        User user = new User();
        user.setName("Laura");
        user.setEmail("laura@mail.com");
        repository.save(user);

        boolean exists = repository.existsByEmail("LauRa@mail.com");

        assertTrue(exists, "It should return true even the email has uppercase letters.");
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEmailDoesNotExist() {

        User user = new User();
        user.setName("Laura");
        user.setEmail("laura@mail.com");
        repository.save(user);

        boolean exists = repository.existsByEmail("wrong@mail.com");

        assertFalse(exists, "It should return false when email does not exist");

    }

    @Test
    void clear_shouldRemoveAllUsers() {
        User u1 = new User();
        User u2 = new User();
        u1.setName("Ana");
        u1.setEmail("ana@mail.com");
        u2.setName("Marc");
        u2.setEmail("marc@mail.com");

        repository.save(u1);
        repository.save(u2);

        assertEquals(2, repository.findAll().size());

        repository.clear();

        assertEquals(0, repository.findAll().size(), "The list should be empty after the clear");
    }
}

