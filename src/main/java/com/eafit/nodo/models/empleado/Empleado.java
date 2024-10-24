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
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @ManyToMany
    @JoinTable(
            name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    )
    private List<Proyecto> proyectos;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Salario salario;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private CuentaBancaria cuentaBancaria;
}
