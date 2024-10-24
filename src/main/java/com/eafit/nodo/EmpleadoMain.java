package com.eafit.nodo;

import com.eafit.nodo.configs.HibernateUtil;
import com.eafit.nodo.models.empleado.*;
import com.eafit.nodo.repositories.empleado.*;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoMain {
    public static void main(String[] args) {
        EntityManager em = HibernateUtil.getEntityManager();
        var departamentoRepository = new DepartamentoRepository(em);
        var empleadoRepository = new EmpleadoRepository(em);
        var proyectoRepository = new ProyectoRepository(em);

        Departamento departamento = createDepartamento(departamentoRepository, "Desarrollo");
        List<Proyecto> proyectos = createProyectos(proyectoRepository, "Proyecto A", "Proyecto B");

        List<Empleado> empleados = createEmpleados(empleadoRepository, departamento, proyectos);
        printEmpleados(empleados);

        em.close();
    }

    private static Departamento createDepartamento(DepartamentoRepository repo, String nombre) {
        Departamento departamento = new Departamento();
        departamento.setNombre(nombre);
        repo.save(departamento);
        return departamento;
    }

    private static List<Proyecto> createProyectos(ProyectoRepository repo, String... nombres) {
        List<Proyecto> proyectos = new ArrayList<>();
        for (String nombre : nombres) {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(nombre);
            repo.save(proyecto);
            proyectos.add(proyecto);
        }
        return proyectos;
    }

    private static List<Empleado> createEmpleados(EmpleadoRepository repo, Departamento departamento, List<Proyecto> proyectos) {
        List<Empleado> empleados = new ArrayList<>();

        empleados.add(createEmpleado(repo, "Maria Correa", "Desarrolladora", departamento, proyectos.subList(0, 1), 3000, "1234567890"));
        empleados.add(createEmpleado(repo, "Isabel Jiménez", "Analista", departamento, proyectos, 3500, "0987654321"));

        return empleados;
    }

    private static Empleado createEmpleado(EmpleadoRepository repo, String nombre, String cargo, Departamento departamento,
                                           List<Proyecto> proyectos, double salarioMonto, String numeroCuenta) {
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setCargo(cargo);
        empleado.setDepartamento(departamento);
        empleado.setProyectos(new ArrayList<>(proyectos));

        Salario salario = new Salario();
        salario.setMonto(salarioMonto);
        salario.setEmpleado(empleado);
        empleado.setSalario(salario);

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setNumeroCuenta(numeroCuenta);
        cuenta.setEmpleado(empleado);
        empleado.setCuentaBancaria(cuenta);

        repo.save(empleado);
        return empleado;
    }

    private static void printEmpleados(List<Empleado> empleados) {
        System.out.println("Lista de empleados:");
        for (Empleado emp : empleados) {
            System.out.printf("Nombre: %s, Cargo: %s, Salario: %.2f, Cuenta: %s%n",
                    emp.getNombre(), emp.getCargo(), emp.getSalario().getMonto(), emp.getCuentaBancaria().getNumeroCuenta());
        }
    }
}