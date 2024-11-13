package com.FoodSpringApp.FoodSpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.FoodSpringApp.FoodSpringApp.model.Alquiler;
import com.FoodSpringApp.FoodSpringApp.service.AlquilerService;

@Controller
@RequestMapping("/api/alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;
    
    @GetMapping
    public ResponseEntity<List<Alquiler>> obtenerTodosAlquileres() {
    List<Alquiler> alquileres = alquilerService.obtenerTodosAlquileres();
    return ResponseEntity.ok(alquileres);
    }

    @GetMapping("/friendly")
    public ResponseEntity<List<Object[]>> obtenerTodosAlquileress() {
        List<Object[]> alquileres = alquilerService.obtenerTodosAlquileresFriendly();
        return ResponseEntity.ok(alquileres);
    }
}