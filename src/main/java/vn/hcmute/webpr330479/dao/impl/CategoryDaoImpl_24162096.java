package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.ICategoryDao_24162096;
import vn.hcmute.webpr330479.entity.Category_24162096;

import java.util.List;

public class CategoryDaoImpl_24162096 implements ICategoryDao_24162096 {

    @Override
    public Category_24162096 findById(int id) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            return em.find(Category_24162096.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category_24162096> findAll() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Category_24162096> query = em.createQuery(
                    "SELECT c FROM Category_24162096 c ORDER BY c.categoryId DESC", Category_24162096.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category_24162096> findAll(int page, int pageSize) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Category_24162096> query = em.createQuery(
                    "SELECT c FROM Category_24162096 c ORDER BY c.categoryId DESC", Category_24162096.class);
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
                    "SELECT COUNT(c) FROM Category_24162096 c", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Category_24162096 category) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(category);
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
    public void update(Category_24162096 category) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(category);
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
            Category_24162096 cat = em.find(Category_24162096.class, id);
            if (cat != null) {
                em.remove(cat);
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
