package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.exception.NotFoundByIdException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<User>();

    @Override
    public User save(User user) {
        if(user.getId() == null) {
            user.setId(UUID.randomUUID());
        }

        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(UUID id) {
        users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundByIdException("User with id '" + id + "' not found"));
        return Optional.empty();
    }

    @Override
    public List<User> searchByName(String name) {
        return List.of();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
