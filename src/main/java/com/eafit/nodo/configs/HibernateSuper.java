package com.eafit.nodo.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateSuper {
    private static final EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

    private static EntityManagerFactory createEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("supermercadoPU");
        } catch (Throwable ex) {
            // Si la creación del EntityManagerFactory falla, se lanza una excepción
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}