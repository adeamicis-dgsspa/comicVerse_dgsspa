package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Fumetto;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class FumettoRepository extends AbstractManagedRepository {

    public List<Fumetto> findAll() {
        return withEntityManager(em ->
                em.createQuery("SELECT f FROM Fumetto f", Fumetto.class)
                        .getResultList()
        );
    }

    public Optional<Fumetto> findById(Integer id) {
        return withEntityManager(em ->
                Optional.ofNullable(em.find(Fumetto.class, id))
        );
    }

    public Fumetto save(Fumetto fumetto) {
        return withEntityManager(em -> {
            if (fumetto.getId() == null) {
                em.persist(fumetto);
                return fumetto;
            } else {
                return em.merge(fumetto);
            }
        });
    }

    public void deleteById(Integer id) {
        executeWithEntityManager(() -> {
            entityManager.getTransaction().begin();
            Fumetto fumetto = entityManager.find(Fumetto.class, id);
            if (fumetto != null) {
                entityManager.remove(fumetto);
            }
            entityManager.getTransaction().commit();
        });
    }

    public boolean existsById(Integer id) {
        return withEntityManager(em ->
                em.find(Fumetto.class, id) != null
        );
    }
}