package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);

    List<User> listUsers(String name);

    User getById(UUID id);

}