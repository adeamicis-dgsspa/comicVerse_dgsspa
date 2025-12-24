package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Fumetto;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.dgsspa.comicverse.repository.AbstractManagedRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class FumettoRepository extends AbstractManagedRepository {
    public List<Fumetto> findAll() {
        return withEntityManager(em ->
                em.createQuery("SELECT f FROM Fumetto f", Fumetto.class)
                        .getResultList()
        );
    }

    public Fumetto findById(Long id) {
        return withEntityManager(em ->
                em.find(Fumetto.class, id)
        );
    }
}