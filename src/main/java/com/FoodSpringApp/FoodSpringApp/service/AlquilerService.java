package com.FoodSpringApp.FoodSpringApp.service;

import com.FoodSpringApp.FoodSpringApp.model.Alquiler;
import com.FoodSpringApp.FoodSpringApp.repository.AlquilerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlquilerService{

    @Autowired
    private AlquilerRepository alquilerRepository;

    public List<Alquiler> obtenerTodosAlquileres() {
        return alquilerRepository.findAll();
    }

    public List<Object[]> obtenerTodosAlquileresFriendly() {
        List<Object[]> alquileresConInfo = alquilerRepository.obtenerAlquileresConClienteYVehiculo();
        List<Object[]> alquileresFriendly = new ArrayList<>();
        for (Object[] alquilerData : alquileresConInfo) {
            alquileresFriendly.add(alquilerData); 
        }
        return alquileresFriendly;
    }
}
