package com.eafit.nodo.models.supermercado;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    // Relación con Marca
    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    // Relación con Venta
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta; // Relación con Venta
}