package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Fumetto;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public List<Fumetto> searchByFiltri(String titolo, LocalDateTime data) {
        return withEntityManager(em -> {
            StringBuilder jpql = new StringBuilder("SELECT f FROM Fumetto f");
            Map<String, Object> params = new LinkedHashMap<>();
            boolean primoFiltro = true;

            if (titolo != null && !titolo.isBlank()) {
                jpql.append(primoFiltro ? " WHERE" : " AND");
                jpql.append(" LOWER(f.titolo) LIKE LOWER(CONCAT(:titolo, '%'))");
                params.put("titolo", titolo);
                primoFiltro = false;
            }

            if (data != null) {
                jpql.append(primoFiltro ? " WHERE" : " AND");
                jpql.append(" f.dataPubblicazione >= :data");
                params.put("data", data);
                primoFiltro = false;
            }

            TypedQuery<Fumetto> query = em.createQuery(jpql.toString(), Fumetto.class);
            params.forEach(query::setParameter);
            return query.getResultList();
        });

    }
}