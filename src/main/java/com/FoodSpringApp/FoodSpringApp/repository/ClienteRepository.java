package com.FoodSpringApp.FoodSpringApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodSpringApp.FoodSpringApp.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}