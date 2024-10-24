package com.eafit.nodo.models.supermercado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime fecha;
    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Relación con Producto
    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>(); // Inicializar la lista

    public void addProducto(Producto producto, int cantidad) {
        // Aquí debes tener en cuenta la cantidad, si se utiliza en el contexto
        productos.add(producto);
        producto.setVenta(this);// Establecer la venta en el producto

    }
}
