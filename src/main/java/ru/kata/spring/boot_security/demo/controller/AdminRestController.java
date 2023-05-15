package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminRestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> allUsers() {
        List<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        List<Role> roleList = roleService.getAllRoles();
        return ResponseEntity.ok(roleList);
    }


    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUsers(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable("id") Long id, Model model) {
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }


}
