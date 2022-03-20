package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    User show(long id);

    void save(User user);

    void update(User updateUser);

    void delete(long id);

    List<User> getAllUsers();

    void addRoleUser(long userId, String roleName);

    User findUserByName(String userName);
}
