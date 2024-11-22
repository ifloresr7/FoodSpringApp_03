package com.FoodSpringApp.FoodSpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.FoodSpringApp.FoodSpringApp.model.Usuario;
import com.FoodSpringApp.FoodSpringApp.model.Vehiculo;
import com.FoodSpringApp.FoodSpringApp.service.UsuarioService;
import com.FoodSpringApp.FoodSpringApp.service.VehiculoService;
import org.springframework.security.core.userdetails.User;

@Controller
public class AppController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;

    private String version = "2024.11.22.18.21";

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
        model.addAttribute("title", "Nuestros mejores vehiculos");
        model.addAttribute("description", "Aquí puedes ver todos los vehículos disponibles para alquilar 🐈.");
        model.addAttribute("currentPage", "vehiculos");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "vehiculos";
    }

    @GetMapping("/mi-perfil")
    public String perfilPage(Model model) {
        String dni = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            dni = ((User) principal).getUsername();
        } else if (principal instanceof String) {
            dni = principal.toString();
        }
        Usuario usuario = usuarioService.obtenerUsuarioPorDni(dni);
        model.addAttribute("usuario", usuario);
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Mi perfil");
        model.addAttribute("description", "Información de mi perfil.");
        model.addAttribute("currentPage", "mi-perfil");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "mi_perfil";
    }

    @GetMapping("/mis-alquileres")
    public String misAlquileresPage(Model model) {
        String dni = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            dni = ((User) principal).getUsername();
        } else if (principal instanceof String) {
            dni = principal.toString();
        }
        model.addAttribute("usuarioID", usuarioService.obtenerUsuarioPorDni(dni).getId());
        model.addAttribute("vehiculos", "awdawdawd,ddwaadwwad,dwadwa,dwa,awd,wad,awd,wda");
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Mis alquileres");
        model.addAttribute("description", "Estos son todos mis alquileres.");
        model.addAttribute("currentPage", "mis-alquileres");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "mis_alquileres";
    }

    @GetMapping("/gestion-alquileres")
    public String alquileresPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Gestión de alquileres");
        model.addAttribute("description", "Aquí puedes ver todos los alquileres.");
        model.addAttribute("currentPage", "gestion-alquileres");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "gestion_alquileres";
    }

    @GetMapping("/gestion-usuarios")
    public String clientesPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Gestión de clientes");
        model.addAttribute("description", "Aquí puedes ver todos los clientes.");
        model.addAttribute("currentPage", "gestion-usuarios");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "gestion_usuarios";
    }

    @GetMapping("/gestion-vehiculos")
    public String gestionVehiculosPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Gestión de Vehículos");
        model.addAttribute("description", "Aquí puedes ver todos los vehículos.");
        model.addAttribute("currentPage", "gestion-vehiculos");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "gestion_vehiculos";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Login");
        model.addAttribute("description", "Inicia sesión.");
        model.addAttribute("currentPage", "login");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "login";
    }

    @GetMapping("/registro")
    public String registroPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("version", this.version);
        model.addAttribute("title", "Registro");
        model.addAttribute("description", "Registrate.");
        model.addAttribute("currentPage", "registro");
        model.addAttribute("role", obtenerRoleDeUsuario());
        return "registro";
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