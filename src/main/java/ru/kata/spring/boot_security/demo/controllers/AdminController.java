package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showUsersList(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("admin", userService.getCurrentUser());
        return "adminPanel";
    }

    @GetMapping("/new")
    public String formAddUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("admin", userService.getCurrentUser());
        model.addAttribute("allRoles", roleService.listRoles());
        return "new";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role") Set<Role> roles) {
        userService.save(user,roles);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") long id, @ModelAttribute("user") @Valid User user,
                             @RequestParam(value = "role", required = false) Set<Role> roles) {
        userService.update(user,roles,id);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user/profile")
    public String profile(ModelMap model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }
}
