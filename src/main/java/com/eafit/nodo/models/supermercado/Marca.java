package com.eafit.nodo.models.supermercado;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    // Relación con Producto
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<Producto> productos;
}
