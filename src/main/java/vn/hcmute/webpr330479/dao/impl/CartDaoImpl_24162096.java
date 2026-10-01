package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.ICartDao_24162096;
import vn.hcmute.webpr330479.entity.Cart_24162096;

import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl_24162096 implements ICartDao_24162096 {

    @Override
    public void insert(Cart_24162096 cart) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Cart_24162096 cart) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(cart);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(String cartId) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Cart_24162096 cart = em.find(Cart_24162096.class, cartId);
            if (cart != null) {
                em.remove(cart);
            }
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Cart_24162096 findById(String cartId) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            return em.find(Cart_24162096.class, cartId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cart_24162096> findByUserId(int userId) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cart_24162096 c WHERE c.user.userId = :userId ORDER BY c.buyDate DESC";
            TypedQuery<Cart_24162096> query = em.createQuery(jpql, Cart_24162096.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cart_24162096> findByUserIdAndStatus(int userId, int status) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cart_24162096 c WHERE c.user.userId = :userId AND c.status = :status ORDER BY c.buyDate DESC";
            TypedQuery<Cart_24162096> query = em.createQuery(jpql, Cart_24162096.class);
            query.setParameter("userId", userId);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cart_24162096> findAll() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cart_24162096 c ORDER BY c.buyDate DESC";
            TypedQuery<Cart_24162096> query = em.createQuery(jpql, Cart_24162096.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
