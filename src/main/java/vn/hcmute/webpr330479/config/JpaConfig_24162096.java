package vn.hcmute.webpr330479.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig_24162096 {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("de05-jpa-24162096");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
