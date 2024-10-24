package com.eafit.nodo.repositories.medicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.medicamento.Sucursal;

import java.util.List;

public class SucursalRepository {
    private final EntityManager entityManager;

    public SucursalRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Sucursal sucursal) {
        entityManager.merge(sucursal);
    }

    public List<Sucursal> findAll() {
        TypedQuery<Sucursal> query = entityManager.createQuery("SELECT s FROM Sucursal s", Sucursal.class);
        return query.getResultList();
    }
}