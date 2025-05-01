package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitUserToDB {
    private final UserService userService;
    private final RoleService roleService;


    public InitUserToDB(UserService userService, RoleService roleService ) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        if (roleService.listRoles().isEmpty()) {
            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");

            // Сохраняем роли
            roleService.add(adminRole);
            roleService.add(userRole);

            // Получаем роли уже из базы (чтобы гарантированно иметь корректные ID)
            Role savedAdminRole = roleService.findByName("ROLE_ADMIN");
            Role savedUserRole = roleService.findByName("ROLE_USER");

            Set<Role> rolesAdmin = new HashSet<>();
            Set<Role> rolesUser = new HashSet<>();
            rolesAdmin.add(savedAdminRole);
            rolesUser.add(savedUserRole);

            User admin = new User("test", "test", "admin@mail.ru", 35, "admin", "admin", rolesAdmin);
            User user = new User("tetasr", "tesi", "user@bk.ru", 30, "user", "user", rolesUser);

            userService.add(admin);
            userService.add(user);
        }
    }

}
