package com.FoodSpringApp.FoodSpringApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosClientes() {
    List<Usuario> usuarios = usuarioService.obtenerTodosClientes();
    return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarCliente(@RequestBody Usuario usuario) {
    Usuario nuevoUsuario = usuarioService.save(usuario);
    return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioData) {
    Usuario usuarioActualizado = usuarioService.update(id, usuarioData);
    return usuarioActualizado != null ? ResponseEntity.ok(usuarioActualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar un cliente y redirigir a la lista de clientes
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * login camino de html
     */
      // Ruta privada
      @GetMapping("/login")
      public String paginaPrivada(@AuthenticationPrincipal User user, Model model) {
          model.addAttribute("username", user.getUsername());
          return "privado";  // Esta es la vista "privado.html"
      }
    
}