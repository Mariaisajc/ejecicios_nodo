package com.eafit.nodo.models.medicamento;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;

    @ManyToOne(optional = false) // La relación es obligatoria
    @JoinColumn(name = "medico_id", nullable = false) // La columna no puede ser nula
    private Medico medico;

    // Relación con Sucursal: Un paciente debe estar asociado a una sucursal
    @ManyToOne(optional = false) // La relación es obligatoria
    @JoinColumn(name = "sucursal_id", nullable = false) // La columna no puede ser nula
    private Sucursal sucursal;

    // Relación con Medicamento: Un paciente debe tener un medicamento asociado
    @ManyToOne(optional = false) // La relación es obligatoria
    @JoinColumn(name = "medicamento_id", nullable = false) // La columna no puede ser nula
    private Medicamento medicamento;
}