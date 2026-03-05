package cat.itacademy.s04.t01.userapi.controller;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.exception.NotFoundByIdException;
import cat.itacademy.s04.t01.userapi.repository.InMemoryUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final InMemoryUserRepository userRepository;

    // Inyección por constructor (Buena práctica)
    public UserController(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String name){
        List<User> users = userRepository.findAll();

        if(name == null || name.isEmpty()) {
            return users;
        }

        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable UUID id){
        return userRepository.findAll().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundByIdException("User with id '" + id + "' not found"));
    }
}
