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

    public Alquiler create(Alquiler alquilerData) {
        if (alquilerData.getClienteId() == 0 || alquilerData.getVehiculoId() == 0 ||
            alquilerData.getFechaInicio() == null || alquilerData.getFechaFin() == null) {
            // Validar que todos los datos necesarios están presentes
            return null;
        }
        // Lógica adicional para calcular el precio, validar fechas, etc., si es necesario
        return alquilerRepository.save(alquilerData);
    }

    public List<Alquiler> obtenerAlquileresPorCliente(int clienteId) {
        return alquilerRepository.findByClienteId(clienteId);
    }
    
}
