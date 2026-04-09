package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Genere;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenereRepository extends AbstractManagedRepository {

    public List<Genere> findAll() {
        return withEntityManager(em ->
                em.createQuery("SELECT g FROM Genere g", Genere.class)
                        .getResultList()
        );
    }

    public Optional<Genere> findById(Integer id) {
        return withEntityManager(em ->
                Optional.ofNullable(em.find(Genere.class, id))
        );
    }

    public Genere save(Genere genere) {
        return withEntityManager(em -> {
            if (genere.getId() == null) {
                em.persist(genere);
                return genere;
            } else {
                return em.merge(genere);
            }
        });
    }

    public boolean deleteById(Integer id) {
        return withEntityManager(em -> {
            Genere genere = em.find(Genere.class, id);
            if (genere != null) {
                em.remove(genere);
                return true;
            }
            return false;
        });
    }
}