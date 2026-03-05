package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public List<User> listUsers(String name) {
        return List.of();
    }

    @Override
    public User getByID(UUID id) {
        return null;
    }

    @Override
    public List<User> searchUsersByName() {
        return List.of();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
