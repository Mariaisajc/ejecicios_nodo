package com.eafit.nodo;

import com.eafit.nodo.configs.HibernateSuper;
import jakarta.persistence.EntityManager;
import com.eafit.nodo.models.supermercado.*;
import com.eafit.nodo.repositories.supermercado.SupermercadoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class SupermercadoMain {
    public static void main(String[] args) {
        EntityManager em = HibernateSuper.getEntityManager();

        SupermercadoRepository repository = new SupermercadoRepository();
        repository.setEntityManager(em);

        // Iniciar una transacción
        em.getTransaction().begin();

        // Crear y persistir una Marca
        Marca marca = new Marca();
        marca.setNombre("Marca A");
        repository.saveMarca(marca);

        Marca marca2 = new Marca();
        marca2.setNombre("Marca B");
        repository.saveMarca(marca2);

        // Crear y persistir un Cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Doralba");
        cliente.setApellido("Correa");
        repository.saveCliente(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Paula");
        cliente2.setApellido("Montoya");
        repository.saveCliente(cliente2);

        // Crear y persistir un Producto
        Producto producto = new Producto();
        producto.setNombre("Producto A");
        producto.setPrecio(10.99);
        producto.setMarca(marca); // Asignar la marca al producto
        repository.saveProducto(producto);

        Producto producto2 = new Producto();
        producto2.setNombre("Producto B");
        producto2.setPrecio(15.49);
        producto2.setMarca(marca2); // Asignar la otra marca al producto
        repository.saveProducto(producto2);

        // Crear y persistir una Venta
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setCliente(cliente); // Asignar el cliente a la venta
        venta.addProducto(producto, 2); // Agregar el producto a la venta
        repository.saveVenta(venta);

        // Confirmar la transacción
        em.getTransaction().commit();


        // Consultar y mostrar todos los Clientes
        List<Cliente> clientes = repository.findAllClientes();
        System.out.println("Clientes:");
        clientes.forEach(c -> System.out.println(c.getNombre() + " " + c.getApellido()));

        // Consultar y mostrar todos los Productos
        List<Producto> productos = repository.findAllProductos();
        System.out.println("\nProductos:");
        productos.forEach(p -> System.out.println(p.getNombre() + " - Precio: " + p.getPrecio()));

        em.close();
        HibernateSuper.shutdown();
    }
}