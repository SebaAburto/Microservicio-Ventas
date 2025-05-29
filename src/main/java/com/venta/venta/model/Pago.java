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
@Table(name="Pago")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Pago {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer pago_id;

    @Column(nullable = false)
    private double monto_pagado;
    
    @Column(nullable = false, length= 30)
    private String metodo_pago;

    @Column(nullable = false, length=20)
    private String estado_pago;

    @Column(nullable = false)
    private Date fecha_pago;
    
    @OneToOne
    private Orden orden;

}