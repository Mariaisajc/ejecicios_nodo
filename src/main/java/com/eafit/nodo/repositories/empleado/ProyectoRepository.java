package com.eafit.nodo.repositories.empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.empleado.Proyecto;

import java.util.List;

public class ProyectoRepository {
    private EntityManager em;

    public ProyectoRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Proyecto proyecto) {
        em.getTransaction().begin();
        em.persist(proyecto);
        em.getTransaction().commit();
    }

    public List<Proyecto> findAll() {
        TypedQuery<Proyecto> query = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
        return query.getResultList();
    }
}
