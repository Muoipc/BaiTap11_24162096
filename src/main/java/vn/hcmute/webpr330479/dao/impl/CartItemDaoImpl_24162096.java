package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.ICartItemDao_24162096;
import vn.hcmute.webpr330479.entity.CartItem_24162096;

import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl_24162096 implements ICartItemDao_24162096 {

    @Override
    public void insert(CartItem_24162096 cartItem) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(cartItem);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(CartItem_24162096 cartItem) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(cartItem);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(String cartItemId) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            CartItem_24162096 item = em.find(CartItem_24162096.class, cartItemId);
            if (item != null) {
                em.remove(item);
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
    public List<CartItem_24162096> findByCartId(String cartId) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            String jpql = "SELECT ci FROM CartItem_24162096 ci WHERE ci.cart.cartId = :cartId";
            TypedQuery<CartItem_24162096> query = em.createQuery(jpql, CartItem_24162096.class);
            query.setParameter("cartId", cartId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}
