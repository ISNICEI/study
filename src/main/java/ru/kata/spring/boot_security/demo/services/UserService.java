package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    void add(User user);

    User getUserById(Long id);

    void update(Long id, User user);

    void delete(Long id);

    User findByUsername(String name);
}
