package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role show(long id);

    void save(Role role);

    void update(Role updateRole);

    void delete(long id);

    List<Role> getAllRoles();

    Set<Role> getAllRolesFromUser(long userId);

    Role getRoleByName(String roleName);
}
