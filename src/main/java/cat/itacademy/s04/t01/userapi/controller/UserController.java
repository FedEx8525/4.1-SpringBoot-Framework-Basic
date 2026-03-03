package cat.itacademy.s04.t01.userapi.controller;

import cat.itacademy.s04.t01.userapi.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final List<User> users = new ArrayList<User>();

    @GetMapping
    public List<User> getAllUsers(){
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        user.setId(UUID.randomUUID());
        users.add(user);
        return user;
    }
}
