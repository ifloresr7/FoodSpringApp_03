package com.FoodSpringApp.FoodSpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodSpringApp.FoodSpringApp.model.Vehiculo;
import com.FoodSpringApp.FoodSpringApp.repository.VehiculoRepository;

@Service
public class VehiculoService{

    @Autowired
    private VehiculoRepository  vehiculoRepository;

    public List<Vehiculo> obtenerTodosVehiculos() {
        return vehiculoRepository.findAll();
    }
    /**
     * para actualizar el vehiculo
     * @param vehiculo
     * @return
     */
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }


    public Vehiculo findById(int id) {
        return vehiculoRepository.findById(id).orElse(null);
    }


    /**
     * 
     */
    public Vehiculo update(int id, Vehiculo vehiculoData) {

        Vehiculo vehiculo = findById(id);
        if (vehiculo != null) {
            vehiculo.setAutonomia_km(vehiculoData.getAutonomia_km());
            vehiculo.setColor(vehiculoData.getColor());
            vehiculo.setMarca(vehiculoData.getMarca());
            vehiculo.setPotencia_cv(vehiculoData.getPotencia_cv());
            vehiculo.setPuertas(vehiculoData.getPuertas());

            return vehiculoRepository.save(vehiculo);
        }
    
        return null; // O lanza una excepción si no existe
    }

    /**
     * por su id si existe se borra
     * @param id
     */
    public void deleteById(int id) {
        vehiculoRepository.deleteById(id);
    }
    
}
