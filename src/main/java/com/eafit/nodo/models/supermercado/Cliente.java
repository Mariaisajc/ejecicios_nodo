package com.eafit.nodo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;



}
