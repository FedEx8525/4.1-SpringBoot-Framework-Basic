package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(UUID id) {
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
