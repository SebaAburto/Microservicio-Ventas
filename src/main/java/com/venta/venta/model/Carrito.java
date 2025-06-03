package com.venta.venta.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name="Carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Carrito {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer carrito_id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Date fecha_creacion;
    
    @Column(nullable = false)
    private Date fecha_modificacion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Tipo_estado tipo_estado;

    @ManyToOne
    private Producto producto;

}