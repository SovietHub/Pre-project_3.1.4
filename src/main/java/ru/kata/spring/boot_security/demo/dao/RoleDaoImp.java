package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

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
        entityManager.merge(role);
    }

    @Override
    public void delete(long id) {
        Role userRole = entityManager.find(Role.class, id);
        entityManager.remove(userRole);
    }

    @Override
    public List<Role> index() {
        List<Role> listRole = new ArrayList<>();
        try {
            listRole = entityManager
                    .createQuery("FROM Role", Role.class)
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return listRole;
        }
    }

    @Override
    public Role getRoleFromUser(String roleName) {
        return  entityManager.find(Role.class, roleName);
    }
}
