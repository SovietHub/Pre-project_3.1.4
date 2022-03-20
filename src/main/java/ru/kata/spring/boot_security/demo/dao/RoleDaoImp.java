package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role show(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void update(Role role) {
        entityManager.createQuery("update Role set roleName = :role where id = :id")
                .setParameter("role", role.getRoleName())
                .setParameter("id",  role.getId())
                .executeUpdate();
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery("delete from Role where id= :id").setParameter("id", (int) id).executeUpdate();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role ", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.find(Role.class, roleName);
    }
}
