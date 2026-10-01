package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.IRoleDao_24162096;
import vn.hcmute.webpr330479.entity.UserRole_24162096;

import java.util.List;

public class RoleDaoImpl_24162096 implements IRoleDao_24162096 {

    @Override
    public UserRole_24162096 findById(int id) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            return em.find(UserRole_24162096.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public UserRole_24162096 findByName(String name) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<UserRole_24162096> query = em.createQuery(
                    "SELECT r FROM UserRole_24162096 r WHERE r.roleName = :name", UserRole_24162096.class);
            query.setParameter("name", name);
            List<UserRole_24162096> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public List<UserRole_24162096> findAll() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<UserRole_24162096> query = em.createQuery(
                    "SELECT r FROM UserRole_24162096 r", UserRole_24162096.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
