package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDao {

    Role show(long id);

    void save(Role role);

    void update(Role role);

    void delete(long id);

    List<Role> getAllRoles();

    Role getRoleByName(String roleName);
}
