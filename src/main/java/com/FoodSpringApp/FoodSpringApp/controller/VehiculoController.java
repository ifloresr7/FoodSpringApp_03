package com.FoodSpringApp.FoodSpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FoodSpringApp.FoodSpringApp.model.Vehiculo;
import com.FoodSpringApp.FoodSpringApp.service.VehiculoService;

@Controller
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;
     
    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerTodosVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodosVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    @PostMapping
    public ResponseEntity<Vehiculo> guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.ok(nuevoVehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable int id, @RequestBody Vehiculo vehiculoData) {
        Vehiculo vehiculoActualizado = vehiculoService.update(id, vehiculoData);
        return vehiculoActualizado != null ? ResponseEntity.ok(vehiculoActualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar un vehiculo y redirigir a la lista de vehiculos
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable int id) {
        vehiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
