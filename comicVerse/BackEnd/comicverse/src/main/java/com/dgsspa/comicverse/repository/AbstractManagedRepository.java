package com.dgsspa.comicverse.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.function.Function;

public abstract class AbstractManagedRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;

    public void executeWithEntityManager(Runnable code) {
        try {
            entityManager = entityManagerFactory.createEntityManager();

            code.run();
        } finally {
            if (entityManager != null) entityManager.close();

        }
    }

    protected <T> T withEntityManager(Function<EntityManager,T> code) {

        if (entityManager == null) {
            throw new IllegalStateException("Entity manager not initialized");
        }
        return code.apply(entityManager);
    }
}