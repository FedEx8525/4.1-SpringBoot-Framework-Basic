package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.exception.EmailExistsException;
import cat.itacademy.s04.t01.userapi.exception.NotFoundByIdException;
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
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistsException("The email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> listUsers(String name) {
        if (name != null && !name.isEmpty()) {
            return userRepository.searchByName(name);
        }
        return userRepository.findAll();
    }

    @Override
    public User getByID(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException("User with id '" + id + "' not found"));
    }
}
