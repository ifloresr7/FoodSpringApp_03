package com.FoodSpringApp.FoodSpringApp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "alquiler")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "vehiculo_id", nullable = false)
    private Long vehiculoId;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "precio",nullable = false)
    private double precio;

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
