package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImp;
import ru.kata.spring.boot_security.demo.dao.UserDaoImp;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private UserDaoImp userDaoImp;

    @Autowired
    private RoleDaoImp roleDaoImp;

    @Override
    public Role show(long id) {
        return roleDaoImp.show(id);
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDaoImp.save(role);
    }

    @Transactional
    @Override
    public void update(Role updateRole) {
        roleDaoImp.update(updateRole);
    }

    @Transactional
    @Override
    public void delete(long id) {
        roleDaoImp.delete(id);
    }

    @Override
    public List<Role> index() {
        return roleDaoImp.index();
    }

    @Transactional
    @Override
    public Set<Role> getAllRoles(long userId) {
        return userDaoImp.gelAllRoles(userId);
    }

    @Override
    public Role getRoleFromUser(String roleName) {
        return roleDaoImp.getRoleFromUser(roleName);
    }
}
