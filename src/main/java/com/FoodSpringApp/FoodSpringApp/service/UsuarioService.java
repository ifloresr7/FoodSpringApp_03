package com.FoodSpringApp.FoodSpringApp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.FoodSpringApp.FoodSpringApp.model.Usuario;
import com.FoodSpringApp.FoodSpringApp.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        System.out.println("Valor recibido en loadUserByUsername: " + dni);

        Usuario usuario = usuarioRepository.findByDni(dni);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con DNI: " + dni);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getDni())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();
    }

    public Usuario save(Usuario usuario) {
        String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encryptedPassword);
        System.out.println("Guardando el usuario: " + usuario);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosClientes() {
        return usuarioRepository.findAll();
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
            if (clienteData.getPassword() != null && !clienteData.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(clienteData.getPassword()));
            } else {
                usuario.setPassword(usuario.getPassword());
            }
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
