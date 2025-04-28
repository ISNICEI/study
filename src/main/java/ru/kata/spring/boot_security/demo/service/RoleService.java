package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> listRoles();

    void add(Role role);

    Role findByName(String name);
}
