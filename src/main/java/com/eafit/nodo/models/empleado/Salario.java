package com.eafit.nodo.models.empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor  // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
public class Salario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;

    @OneToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}

