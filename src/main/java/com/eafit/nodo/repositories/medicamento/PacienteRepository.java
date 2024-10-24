package com.eafit.nodo.repositories.medicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.medicamento.Paciente;

import java.util.List;

public class PacienteRepository {
    private final EntityManager entityManager;

    public PacienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Paciente paciente) {
        entityManager.merge(paciente);
    }

    public List<Paciente> findAll() {
        TypedQuery<Paciente> query = entityManager.createQuery("SELECT p FROM Paciente p", Paciente.class);
        return query.getResultList();
    }
}