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

import com.FoodSpringApp.FoodSpringApp.model.Cliente;
import com.FoodSpringApp.FoodSpringApp.service.ClienteService;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosClientes() {
    List<Cliente> clientes = clienteService.obtenerTodosClientes();
    return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
    Cliente nuevoCliente = clienteService.save(cliente);
    return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente clienteData) {
    Cliente clienteActualizado = clienteService.update(id, clienteData);
    return clienteActualizado != null ? ResponseEntity.ok(clienteActualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar un cliente y redirigir a la lista de clientes
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}