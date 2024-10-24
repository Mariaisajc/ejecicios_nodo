package com.eafit.nodo.repositories.medicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.medicamento.Medico;

import java.util.List;

public class MedicoRepository {
    private final EntityManager entityManager;

    public MedicoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Medico medico) {
        entityManager.merge(medico);
    }

    public List<Medico> findAll() {
        TypedQuery<Medico> query = entityManager.createQuery("SELECT m FROM Medico m", Medico.class);
        return query.getResultList();
    }
}