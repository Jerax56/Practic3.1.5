package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUsers(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public User getUser(int ID) {
        return entityManager.find(User.class, ID);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(int ID) {
        entityManager.remove(getUser(ID));
        entityManager.flush();
    }

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("select user from User user where user.name = :name", User.class);
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }
}
