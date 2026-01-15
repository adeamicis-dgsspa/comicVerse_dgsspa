package com.dgsspa.comicverse.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.function.Function;

public abstract class AbstractManagedRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <T> T withEntityManager(Function<EntityManager, T> code) {
        if (entityManager == null) {
            throw new IllegalStateException("Entity manager not initialized");
        }
        return code.apply(entityManager);
    }
}
