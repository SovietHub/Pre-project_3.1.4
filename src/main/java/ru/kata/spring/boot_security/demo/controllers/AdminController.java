package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String all(Model model) {
        model.addAttribute("roles", roleService.index());
        model.addAttribute("users", userService.index());
        model.addAttribute("user", new User());
        model.addAttribute("role", new Role());
        return "panelAdmin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(name = "roleName") String roleName) {
        user.addRoleUser(roleService.getRoleFromUser(roleName));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update")
    public String updateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("roles", roleService.index());
        User user = userService.show(id);
        model.addAttribute("userUpdate", user);
        return "updateUser";
    }

    @PostMapping(value = "/saveUser")
    public String updateUser(@ModelAttribute(name = "userUp") User user, @RequestParam(name = "roleName", required = false) String roleName) {
        if (roleName == null) {
            user.setRoles(userService.show(user.getId()).getRoles());
            userService.update(user);
        } else {
            user.addRoleUser(roleService.getRoleFromUser(roleName));
            userService.update(user);
        }
        return "redirect:/admin";
    }
}
