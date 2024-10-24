package com.eafit.nodo.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateMedi {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("medicamentoPU");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}