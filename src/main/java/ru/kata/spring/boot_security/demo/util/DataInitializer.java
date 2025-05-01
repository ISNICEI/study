/*

package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    @Transactional
    public void init() {
        // Создаем роли, если их нет
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        Role userRole = createRoleIfNotFound("ROLE_USER");

        // Создаем пользователей
        createUserIfNotFound("admin", "admin123", Set.of(adminRole, userRole));
        createUserIfNotFound("user", "user123", Set.of(userRole));
    }

    private Role createRoleIfNotFound(String name) {
        Role role = roleService.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleService.add(role);
        }
        return role;
    }

    private void createUserIfNotFound(String username, String password, Set<Role> roles) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRoles(roles);
            userService.add(user);
        }
    }
}
*/
