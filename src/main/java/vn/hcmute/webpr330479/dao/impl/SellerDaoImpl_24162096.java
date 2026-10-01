package vn.hcmute.webpr330479.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.hcmute.webpr330479.config.JpaConfig_24162096;
import vn.hcmute.webpr330479.dao.ISellerDao_24162096;
import vn.hcmute.webpr330479.entity.Seller_24162096;

import java.util.List;

public class SellerDaoImpl_24162096 implements ISellerDao_24162096 {

    @Override
    public Seller_24162096 findById(int id) {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            return em.find(Seller_24162096.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Seller_24162096> findAll() {
        EntityManager em = JpaConfig_24162096.getEntityManager();
        try {
            TypedQuery<Seller_24162096> query = em.createQuery(
                    "SELECT s FROM Seller_24162096 s WHERE s.status = 1", Seller_24162096.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
