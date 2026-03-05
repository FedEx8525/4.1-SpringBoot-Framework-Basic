package cat.itacademy.s04.t01.userapi.controller;

import cat.itacademy.s04.t01.userapi.entity.User;
import cat.itacademy.s04.t01.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String name){

        return userService.listUsers(name);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User savedUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable UUID id){
        return userService.getByID(id);
    }
}
