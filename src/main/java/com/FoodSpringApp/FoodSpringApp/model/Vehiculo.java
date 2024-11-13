package com.FoodSpringApp.FoodSpringApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "puertas", nullable = false)
    private int puertas;
 
    @Column(name = "autonomia_km", nullable = false)
    private int autonomia_km;

    @Column(name = "potencia_cv", nullable = false)
    private int potencia_cv;

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public int getAutonomia_km() {
        return autonomia_km;
    }

    public void setAutonomia_km(int autonomia_km) {
        this.autonomia_km = autonomia_km;
    }

    public int getPotencia_cv() {
        return potencia_cv;
    }

    public void setPotencia_cv(int potencia_cv) {
        this.potencia_cv = potencia_cv;
    }
}