package com.venta.venta.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<Producto> producto;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
} 
