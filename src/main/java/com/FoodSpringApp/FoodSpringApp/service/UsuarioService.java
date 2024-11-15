package com.FoodSpringApp.FoodSpringApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FoodSpringApp.FoodSpringApp.model.Usuario;
import com.FoodSpringApp.FoodSpringApp.repository.UsuarioRepository;
@Service
public class UsuarioService  implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByDni(dni);
    
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con DNI: " + dni);
        }
    
        // Configura el usuario con los roles obtenidos desde la base de datos
        return User.builder()
                .username(usuario.getDni())
                .password(usuario.getPassword())  // Asegúrate de que la contraseña esté encriptada
                .roles(usuario.getRole())         // Asigna el rol del usuario desde la base de datos
                .build();
    }
    

    public List<Usuario> obtenerTodosClientes() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario update(int id, Usuario clienteData) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            usuario.setNombre(clienteData.getNombre());
            usuario.setApellidos(clienteData.getApellidos());
            usuario.setEmail(clienteData.getEmail());
            usuario.setTelefono(clienteData.getTelefono());
            usuario.setDireccion(clienteData.getDireccion());
            usuario.setPassword(clienteData.getPassword());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}