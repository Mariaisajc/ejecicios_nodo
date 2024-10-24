package com.eafit.nodo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.eafit.nodo.models.medicamento.*;
import com.eafit.nodo.repositories.medicamento.*;


import java.util.ArrayList;
import java.util.UUID;

public class MedicamentoMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("medicamentoPU");

        try (EntityManager entityManager = emf.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            try {
                // Crear repositorios
                MedicoRepository medicoRepository = new MedicoRepository(entityManager);
                PacienteRepository pacienteRepository = new PacienteRepository(entityManager);
                MedicamentoRepository medicamentoRepository = new MedicamentoRepository(entityManager);
                SucursalRepository sucursalRepository = new SucursalRepository(entityManager);

                // Crear medicamentos
                Medicamento medicamento1 = new Medicamento(UUID.randomUUID(), "Ibuprofeno", "Antiinflamatorio", new ArrayList<>());
                Medicamento medicamento2 = new Medicamento(UUID.randomUUID(), "Paracetamol", "Analgésico", new ArrayList<>());

                // Crear sucursales y asociar medicamentos
                Sucursal sucursal1 = new Sucursal(UUID.randomUUID(), "Sucursal Central", "Av. Principal 123", new ArrayList<>());
                Sucursal sucursal2 = new Sucursal(UUID.randomUUID(), "Sucursal Norte", "Calle Secundaria 456", new ArrayList<>());

                // Agregar medicamentos a las sucursales
                sucursal1.getMedicamentos().add(medicamento1);
                sucursal2.getMedicamentos().add(medicamento2);

                // Persistir medicamentos (esto asegurará que están en la base de datos)
                medicamentoRepository.save(medicamento1);
                medicamentoRepository.save(medicamento2);

                // Persistir sucursales (esto persistirá también los medicamentos debido al CascadeType.ALL)
                sucursalRepository.save(sucursal1);
                sucursalRepository.save(sucursal2);

                // Crear médicos
                Medico medico1 = new Medico(UUID.randomUUID(), "Dr. Juan Pérez", "Cardiología", new ArrayList<>());
                Medico medico2 = new Medico(UUID.randomUUID(), "Dra. Ana Gómez", "Pediatría", new ArrayList<>());

                // Crear pacientes y asociar médicos y sucursales
                Paciente paciente1 = new Paciente(UUID.randomUUID(), "Carlos Rodríguez", medico1, sucursal1, medicamento1);
                Paciente paciente2 = new Paciente(UUID.randomUUID(), "Laura Fernández", medico2, sucursal2, medicamento2);

                // Asociar pacientes a médicos
                medico1.getPacientes().add(paciente1);
                medico2.getPacientes().add(paciente2);

                // Persistir médicos y pacientes (esto también persistirá las relaciones)
                medicoRepository.save(medico1);
                medicoRepository.save(medico2);
                pacienteRepository.save(paciente1);
                pacienteRepository.save(paciente2);

                // Confirmar la transacción
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        } finally {
            emf.close(); // Cerrar el EntityManagerFactory
        }
    }}