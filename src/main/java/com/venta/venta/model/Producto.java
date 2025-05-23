package com.inventario.inventario.model;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name="Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Producto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer producto_id;

    @Column(nullable = false, length= 100)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false, length= 50)
    private String categoria;

    @Column(nullable = false, length= 50)
    private String color;

    @Column(nullable = false)
    private Date fecha_creacion;
    
    @OneToOne
    private Tipo_talla tipo_talla;

}


