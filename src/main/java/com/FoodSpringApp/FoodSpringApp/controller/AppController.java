package com.FoodSpringApp.FoodSpringApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.FoodSpringApp.FoodSpringApp.service.AlquilerService;
import com.FoodSpringApp.FoodSpringApp.service.UsuarioService;
import com.FoodSpringApp.FoodSpringApp.service.VehiculoService;

@Controller
public class AppController {

    @Autowired
    private AlquilerService alquilerService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;

    private String version = "2024.11.13.18.45";

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Página de Inicio");
        model.addAttribute("description", "¡Bienvenido a FoodSpringApp!");
        model.addAttribute("currentPage", "home");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "home";
    }
    
    @GetMapping("/vehiculos")
    public String vehiculosPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("vehiculos", vehiculoService.obtenerTodosVehiculos());
        model.addAttribute("title", "Gestión de Vehículos");
        model.addAttribute("description", "Aquí puedes ver todos los vehículos.");
        model.addAttribute("currentPage", "vehiculos");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "vehiculos";
    }
    
    @GetMapping("/usuarios")
    public String clientesPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("clientes", usuarioService.obtenerTodosClientes());
        model.addAttribute("title", "Usuarios");
        model.addAttribute("description", "Aquí puedes ver todos los clientes.");
        model.addAttribute("currentPage", "clientes");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "usuarios";
    }
    
    @GetMapping("/alquileres")
    public String alquileresPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("alquileres", alquilerService.obtenerTodosAlquileres());
        model.addAttribute("title", "Alquileres");
        model.addAttribute("description", "Aquí puedes ver todos los alquileres.");
        model.addAttribute("currentPage", "alquileres");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "alquileres";
    }
    
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("alquileres", alquilerService.obtenerTodosAlquileres());
        model.addAttribute("title", "Login");
        model.addAttribute("description", "Inicia sesión.");
        model.addAttribute("currentPage", "login");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "login";
    }

    private String obtenerRoleDeUsuario() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = "ROLE_ANONYMOUS";
    
        if (authentication != null && authentication.isAuthenticated()) {
            role = authentication.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .findFirst()
                    .orElse("ROLE_ANONYMOUS");
        }
        return role;
    }
}