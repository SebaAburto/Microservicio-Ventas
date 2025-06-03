package com.venta.venta.model;

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
@Table(name="Tipo_estado")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tipo_estado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tipo_estado_id;

    @Column(nullable = false)
    private String descripcion; //activo, abandonado, finalizado

}