package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Fumetto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public boolean deleteById(Integer id) {
        return withEntityManager(em -> {
            Fumetto fumetto = em.find(Fumetto.class, id);
            if (fumetto != null) {
                em.remove(fumetto);
                return true;
            }
            return false;
        });
    }

    /*public List<Fumetto> findByTitoloStartingWith(String prefisso) {
        return withEntityManager(em ->
                em.createQuery(
                                "SELECT f FROM Fumetto f " +
                                        "WHERE LOWER(f.titolo) LIKE LOWER(CONCAT(:prefisso, '%'))",
                                Fumetto.class
                        )
                        .setParameter("prefisso", prefisso)
                        .getResultList()
        );
    }*/

    public List<Fumetto> findByDataPubblicazioneAfter(LocalDateTime data) {
        return withEntityManager(em ->
                em.createQuery(
                                "SELECT f FROM Fumetto f WHERE f.dataPubblicazione > :data",
                                Fumetto.class
                        )
                        .setParameter("data", data)
                        .getResultList()
        );
    }
}
