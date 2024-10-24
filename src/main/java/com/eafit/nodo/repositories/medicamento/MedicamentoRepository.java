package com.eafit.nodo.repositories.medicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.medicamento.Medicamento;

import java.util.List;

public class MedicamentoRepository {
    private final EntityManager entityManager;

    public MedicamentoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Medicamento medicamento) {
        entityManager.merge(medicamento);
    }

    public List<Medicamento> findAll() {
        TypedQuery<Medicamento> query = entityManager.createQuery("SELECT m FROM Medicamento m", Medicamento.class);
        return query.getResultList();
    }
}