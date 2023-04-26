package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("admin", userService.getUsers());
        return "admin";
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("newUser", new User());
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {
        if (user.getId() == 0) {
            userService.saveUsers(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/updateUser")
    public String getUserById(@RequestParam("id") int id, Model model) {
        model.addAttribute("newUser", userService.getUser(id));
        return "user-info";
    }

    @PatchMapping(value = "/updateUser")
    public String updateUser(@RequestParam("id") int id, @RequestBody User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
