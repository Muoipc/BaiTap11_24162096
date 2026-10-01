package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.IUserDao_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;

import java.util.List;

public class UserDaoImpl_24162096 implements IUserDao_24162096 {

    @Override
    public User_24162096 findById(int id) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            return em.find(User_24162096.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public User_24162096 findByUsername(String username) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<User_24162096> query = em.createQuery(
                    "SELECT u FROM User_24162096 u WHERE u.username = :username", User_24162096.class);
            query.setParameter("username", username);
            List<User_24162096> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User_24162096 findByEmail(String email) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<User_24162096> query = em.createQuery(
                    "SELECT u FROM User_24162096 u WHERE u.email = :email", User_24162096.class);
            query.setParameter("email", email);
            List<User_24162096> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User_24162096 login(String login, String password) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<User_24162096> query = em.createQuery(
                    "SELECT u FROM User_24162096 u WHERE (u.username = :login OR u.email = :login) AND u.password = :password",
                    User_24162096.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User_24162096> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(User_24162096 user) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User_24162096 user) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return findByEmail(email) != null;
    }

    @Override
    public List<User_24162096> findAll() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<User_24162096> query = em.createQuery(
                    "SELECT u FROM User_24162096 u", User_24162096.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
