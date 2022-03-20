package ru.kata.spring.boot_security.demo.dao;

import org.hibernate.HibernateException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Query(nativeQuery = true)
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        try {
            list = entityManager
                    .createQuery("FROM User", User.class)
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    @Override
    public User show(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    public void delete(long id) {
        User userDelete = entityManager.find(User.class, id);
        entityManager.remove(userDelete);
    }

    @Override
    public Set<Role> gelAllRoles(long userId) {
        return entityManager.find(User.class, userId).getRoles();
    }

    @Override
    public void addRoleUser(long userId, Role role) {
        entityManager.find(User.class, userId).addRoleUser(role);
    }

    @Override
    public User findByName(String userName) {
        return (User) entityManager.createQuery("FROM User where name = :name").setParameter("name", userName).getSingleResult();
    }


}
