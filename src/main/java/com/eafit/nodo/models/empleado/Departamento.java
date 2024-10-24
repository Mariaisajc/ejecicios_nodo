package com.eafit.nodo.models.empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@NoArgsConstructor  // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Empleado> empleados;
}
