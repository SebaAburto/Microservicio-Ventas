package com.venta.venta.model;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name="Orden")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Orden {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orden_id;

    @Column(nullable = false)
    private double precio_unitario;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Date fecha_creacion;

    @ManyToMany
    private Producto producto;

    @OneToMany
    private Carrito carrito;

    @OneToMany
    private Usuario usuario;
} 
