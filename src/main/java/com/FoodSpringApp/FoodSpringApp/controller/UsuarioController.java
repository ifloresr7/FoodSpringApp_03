package com.FoodSpringApp.FoodSpringApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FoodSpringApp.FoodSpringApp.model.Usuario;
import com.FoodSpringApp.FoodSpringApp.service.UsuarioService;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosClientes() {
        List<Usuario> usuarios = usuarioService.obtenerTodosClientes();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/save-client")
    public ResponseEntity<Map<String, String>> guardarCliente(@RequestBody Usuario usuario) {
        Map<String, String> response = new HashMap<>();
        logger.info("Nuevo Usuario recibido: {}", usuario.getNombre());  // Log info
        try {
            usuario.setRole("ROLE_USER");
            Usuario nuevoUsuario = usuarioService.save(usuario);
            response.put("message", "Usuario registrado con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo un error al registrar el usuario: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioData) {
        Usuario usuarioActualizado = usuarioService.update(id, usuarioData);
        return usuarioActualizado != null ? ResponseEntity.ok(usuarioActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        if (usuarioService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}