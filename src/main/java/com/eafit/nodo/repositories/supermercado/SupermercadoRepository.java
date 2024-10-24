package com.eafit.nodo.repositories.supermercado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.eafit.nodo.models.supermercado.*;

import java.util.List;

public class SupermercadoRepository {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Métodos para Cliente
    public void saveCliente(Cliente cliente) {
        entityManager.persist(cliente);
    }

    public List<Cliente> findAllClientes() {
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }

    // Métodos para Producto
    public void saveProducto(Producto producto) {
        entityManager.persist(producto);
    }

    public List<Producto> findAllProductos() {
        TypedQuery<Producto> query = entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    // Métodos para Marca
    public void saveMarca(Marca marca) {
        entityManager.persist(marca);
    }

    public List<Marca> findAllMarcas() {
        TypedQuery<Marca> query = entityManager.createQuery("SELECT m FROM Marca m", Marca.class);
        return query.getResultList();
    }

    // Métodos para Venta
    public void saveVenta(Venta venta) {
        entityManager.persist(venta);
    }

    public List<Venta> findAllVentas() {
        TypedQuery<Venta> query = entityManager.createQuery("SELECT v FROM Venta v", Venta.class);
        return query.getResultList();
    }
}
