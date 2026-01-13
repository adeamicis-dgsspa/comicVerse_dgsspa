package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Genere;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.dgsspa.comicverse.repository.AbstractManagedRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class GenereRepository extends AbstractManagedRepository {
    public List<Genere> findAll() {
        return withEntityManager(em ->
                em.createQuery("SELECT g FROM Genere g", Genere.class)
                        .getResultList()
        );
    }

    public Genere findById(Long id) {
        return withEntityManager(em ->
                em.find(Genere.class, id)
        );
    }
}
