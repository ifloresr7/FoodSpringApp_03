package com.FoodSpringApp.FoodSpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodSpringApp.FoodSpringApp.model.Cliente;
import com.FoodSpringApp.FoodSpringApp.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente update(int id, Cliente clienteData) {
        Cliente cliente = findById(id);
        if (cliente != null) {
            cliente.setNombre(clienteData.getNombre());
            cliente.setApellidos(clienteData.getApellidos());
            cliente.setEmail(clienteData.getEmail());
            cliente.setTelefono(clienteData.getTelefono());
            cliente.setDireccion(clienteData.getDireccion());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void deleteById(int id) {
        clienteRepository.deleteById(id);
    }

    public Cliente findById(int id) {
        return clienteRepository.findById(id).orElse(null);
    }
}