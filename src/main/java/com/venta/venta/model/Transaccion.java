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
@Table(name="Transaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaccion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer transaccion_id;

     @Column(nullable = false, length = 100, unique = true)
    private String codigoTransaccion; // Código único de la transaccion

    @Column(nullable = false)
    private String entidad_financiera; //Visa, Paypal, Stripe

    @Column(nullable = false, length = 20)
    private String estado; //procesada, fallida, pendiente

    @Column(nullable = false)
    private Date fecha_transaccion;
    
    @ManyToOne
    private Pago pago;

}