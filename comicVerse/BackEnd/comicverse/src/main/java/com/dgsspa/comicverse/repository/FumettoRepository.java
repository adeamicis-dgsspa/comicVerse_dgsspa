package com.dgsspa.comicverse.repository;

import com.dgsspa.comicverse.model.Fumetto;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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

    public List<Fumetto> searchByFiltri(String titolo) {
        return withEntityManager(em -> {
            StringBuilder jpql = new StringBuilder("SELECT f FROM Fumetto f WHERE 1=1");
            Map<String, Object> params = new LinkedHashMap<>();

            if (titolo != null && !titolo.isBlank()) {
                jpql.append(" AND LOWER(f.titolo) LIKE LOWER(CONCAT(:titolo, '%'))");
                params.put("titolo", titolo);
            }

            TypedQuery<Fumetto> query = em.createQuery(jpql.toString(), Fumetto.class);
            params.forEach(query::setParameter);
            return query.getResultList();
        });
    }
}