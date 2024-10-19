import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.eafit.nodo.models.supermercado.*;
import com.eafit.nodo.repositories.supermercado.SupermercadoRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Cargar las variables de entorno desde el archivo .env
        Dotenv dotenv = Dotenv.load();

        // Crear el EntityManagerFactory usando el archivo persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("supermercadoPU");
        EntityManager em = emf.createEntityManager();

        // Crear el repositorio general
        SupermercadoRepository repo = new SupermercadoRepository();
        repo.setEntityManager(em);

        // Iniciar una transacción
        em.getTransaction().begin();

        // Persistir un cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellido("Fernandez");
        repo.saveCliente(cliente);

        // Persistir una marca
        Marca marca = new Marca();
        marca.setNombre("Marca A");
        repo.saveMarca(marca);

        // Persistir un producto
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setMarca(marca);
        repo.saveProducto(producto);

        // Persistir una venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.addProducto(producto);
        repo.saveVenta(venta);

        // Hacer commit de la transacción
        em.getTransaction().commit();

        // Consultar y mostrar todos los clientes
        List<Cliente> clientes = repo.findAllClientes();
        System.out.println("Clientes en la base de datos:");
        clientes.forEach(c -> System.out.println(c.getNombre() + " " + c.getApellido()));

        // Cerrar EntityManager
        em.close();
        emf.close();
    }
}
