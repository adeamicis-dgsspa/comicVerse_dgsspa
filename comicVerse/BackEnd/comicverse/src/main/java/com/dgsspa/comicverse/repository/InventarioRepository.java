package com.dgsspa.comicverse.repository;
import com.dgsspa.comicverse.model.Inventario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InventarioRepository extends AbstractManagedRepository{
    public List<Inventario> findAll() {
        return withEntityManager(em ->
                em.createQuery("SELECT i FROM Inventario i", Inventario.class)
                        .getResultList()
        );
    }


    public Optional<Inventario> findById(Integer id) {
        return withEntityManager(em ->
                Optional.ofNullable(em.find(Inventario.class, id))
        );
    }

    public Inventario save(Inventario inventario) {
        return withEntityManager(em -> {
            if (inventario.getId() == null) {
                em.persist(inventario);
                return inventario;
            } else {
                return em.merge(inventario);
            }
        });
    }

    public boolean deleteById(Integer id) {
        return withEntityManager(em -> {
            Inventario inventario = em.find(Inventario.class, id);
            if (inventario != null) {
                em.remove(inventario);
                return true;
            }
            return false;
        });
    }

    public Integer contaTotaleArticoli(){
        return withEntityManager(em ->
                em.createQuery(
                                "SELECT SUM(i.giacenza) FROM Inventario i",
                                Long.class)
                        .getSingleResult()
                        .intValue()
        );
    }


}
