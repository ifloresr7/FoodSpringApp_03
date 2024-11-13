package com.FoodSpringApp.FoodSpringApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FoodSpringApp.FoodSpringApp.model.Alquiler;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
    @Query("SELECT a.id AS alquiler_id, c.nombre AS nombre_cliente, a.vehiculoId, v.marca AS marca_vehiculo, a.fechaInicio, a.fechaFin FROM Alquiler a JOIN Cliente c ON a.clienteId = c.id JOIN Vehiculo v ON a.vehiculoId = v.id")
    List<Object[]> obtenerAlquileresConClienteYVehiculo();
}