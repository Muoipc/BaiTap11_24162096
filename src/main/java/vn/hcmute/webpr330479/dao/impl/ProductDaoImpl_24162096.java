package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.IProductDao_24162096;
import vn.hcmute.webpr330479.entity.Product_24162096;

import java.util.List;

public class ProductDaoImpl_24162096 implements IProductDao_24162096 {

    @Override
    public Product_24162096 findById(int id) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            return em.find(Product_24162096.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product_24162096> findAll() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Product_24162096> query = em.createQuery(
                    "SELECT p FROM Product_24162096 p ORDER BY p.productId DESC", Product_24162096.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product_24162096> findAll(int page, int pageSize) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Product_24162096> query = em.createQuery(
                    "SELECT p FROM Product_24162096 p ORDER BY p.productId DESC", Product_24162096.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(p) FROM Product_24162096 p", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product_24162096> findBySellerId(int sellerId) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Product_24162096> query = em.createQuery(
                    "SELECT p FROM Product_24162096 p WHERE p.seller.sellerId = :sellerId ORDER BY p.productId ASC",
                    Product_24162096.class);
            query.setParameter("sellerId", sellerId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Product_24162096 product) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(product);
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
    public void update(Product_24162096 product) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(product);
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
    public void delete(int id) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Product_24162096 p = em.find(Product_24162096.class, id);
            if (p != null) {
                em.remove(p);
            }
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
}
