package ru.kata.spring.boot_security.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String listUsers(Model model) {
      model.addAttribute("users", userService.listUsers());
      return "users/index";
  }

  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("user", new User());
    return "users/add";
  }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "users/add";
        }
        userService.add(user);
        return "redirect:/users";
    }

  @GetMapping("/edit")
  public String editForm(@RequestParam Long id, Model model) {
    model.addAttribute("user", userService.getUserById(id));
    return "users/edit";
  }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        userService.update(user.getId(), user);
        return "redirect:/users";
    }

  @GetMapping("/delete")
  public String deleteUser(@RequestParam Long id) {
    userService.delete(id);
    return "redirect:/users";
  }
}
