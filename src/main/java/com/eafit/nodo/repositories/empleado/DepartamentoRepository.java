package com.eafit.nodo.repositories.empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.empleado.Departamento;


import java.util.List;

public class DepartamentoRepository {
    private EntityManager em;

    public DepartamentoRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Departamento departamento) {
        em.getTransaction().begin();
        em.persist(departamento);
        em.getTransaction().commit();
    }

    public List<Departamento> findAll() {
        TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d", Departamento.class);
        return query.getResultList();
    }
}
