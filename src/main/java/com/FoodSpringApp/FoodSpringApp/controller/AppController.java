package com.FoodSpringApp.FoodSpringApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.FoodSpringApp.FoodSpringApp.service.AlquilerService;
import com.FoodSpringApp.FoodSpringApp.service.ClienteService;
import com.FoodSpringApp.FoodSpringApp.service.VehiculoService;

@Controller
public class AppController {

    @Autowired
    private AlquilerService alquilerService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VehiculoService vehiculoService;

    private String version = "2024.11.08.17.35";

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Página de Inicio");
        model.addAttribute("description", "¡Bienvenido a FoodSpringApp!");
        model.addAttribute("currentPage", "home");
        return "home";
    }
    
    @GetMapping("/vehiculos")
    public String vehiculosPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosVehiculos());
        model.addAttribute("title", "Gestión de Vehículos");
        model.addAttribute("description", "Aquí puedes ver todos los vehículos.");
        model.addAttribute("currentPage", "vehiculos");
        return "vehiculos";
    }
    
    @GetMapping("/clientes")
    public String clientesPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("clientes", clienteService.obtenerTodosClientes());
        model.addAttribute("title", "Clientes");
        model.addAttribute("description", "Aquí puedes ver todos los clientes.");
        model.addAttribute("currentPage", "clientes");
        return "clientes";
    }
    
    @GetMapping("/alquileres")
    public String alquileresPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("alquileres", alquilerService.obtenerTodosAlquileres());
        model.addAttribute("title", "Alquileres");
        model.addAttribute("description", "Aquí puedes ver todos los alquileres.");
        model.addAttribute("currentPage", "alquileres");
        return "alquileres";
    }
}