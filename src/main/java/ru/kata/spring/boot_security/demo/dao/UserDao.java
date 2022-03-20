package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    List<User> getAllUsers();

    User show(long id);

    void save(User user);

    void update(User updateUser);

    void delete(long id);

    Set<Role> gelAllRoles(long userId);

    void addRoleUser(long userId, Role role);

    User findByName(String userName);

}
