package ru.kata.spring.boot_security.demo.Util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;

@Component
public class CreateUser implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    public CreateUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        // логин = user пароль = user
        User user1 = new User("user", "surname", "$2a$12$CSzw14uSo5EiEHW8dHGK0OHZhOT4iMrHh8eowb.s38VC/0BHvu.t6");
        // логин = admin пароль = admin
        User admin1 = new User("admin", "surname", "$2a$12$dqBIUBZcU31H3XOuGaLkU.i4PjF9Ss2EE7xco9hElWCvLWb/OLDHe");
        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);
        user1.setRoles(roleUser);
        admin1.setRoles(roleAdmin);
        admin1.setRoles(roleUser);
        userService.saveUsers(user1);
        userService.saveUsers(admin1);
    }
}
