package com.eafit.nodo.models.medicamento;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "medicamentos") // Cambiar a 'sucursales' en la clase Sucursal
    private List<Sucursal> sucursales; // Cambiar a una lista de Sucursal
}
