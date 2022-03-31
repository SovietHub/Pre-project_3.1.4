package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoleService roleService;

    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public List<User> allUsers() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

    @GetMapping("/info")
    public User information(Principal principal) {
        User userSeek = userService.findUserByName(principal.getName());
        return userSeek;
    }

    @GetMapping("users/{id}")
    public User informationById(@PathVariable long id) {
        User userSeek = userService.show(id);
        return userSeek;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        if (user.getRole() == null) {
            user.setRoles(userService.show(user.getId()).getRoles());
            userService.update(user);
        } else {
            user.addRoleToUser(roleService.getRoleByName(user.getRole()));
            userService.update(user);
        }
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.addRoleToUser(roleService.getRoleByName(user.getRole()));
        userService.save(user);
        return user;
    }
}
