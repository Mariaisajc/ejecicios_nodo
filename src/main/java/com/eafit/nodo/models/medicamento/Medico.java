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
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;
    private String especialidad;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;
}
