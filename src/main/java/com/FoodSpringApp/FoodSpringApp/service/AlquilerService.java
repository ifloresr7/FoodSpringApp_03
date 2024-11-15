package com.FoodSpringApp.FoodSpringApp.service;

import com.FoodSpringApp.FoodSpringApp.model.Alquiler;
import com.FoodSpringApp.FoodSpringApp.repository.AlquilerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerService{

    @Autowired
    private AlquilerRepository alquilerRepository;

    public List<Alquiler> obtenerTodosAlquileres() {
        return alquilerRepository.findAll();
    }
}
