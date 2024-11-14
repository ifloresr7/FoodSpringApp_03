package com.FoodSpringApp.FoodSpringApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodSpringApp.FoodSpringApp.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Usuario findByUsername(String username); 
}