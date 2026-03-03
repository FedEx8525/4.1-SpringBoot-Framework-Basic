package cat.itacademy.s04.t01.userapi.controller;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.exception.NotFoundByIdException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final List<User> users = new ArrayList<User>();

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String name){
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
        users.add(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable UUID id){
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundByIdException("User with id '" + id + "' not found"));
    }
}
