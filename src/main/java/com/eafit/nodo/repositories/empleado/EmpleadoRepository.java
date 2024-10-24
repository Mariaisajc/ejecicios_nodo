package com.eafit.nodo.repositories.empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.empleado.Empleado;

import java.util.List;

public class EmpleadoRepository {
    private EntityManager em;

    public EmpleadoRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Empleado empleado) {
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    }

    public List<Empleado> findAll() {
        TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
        return query.getResultList();
    }
}