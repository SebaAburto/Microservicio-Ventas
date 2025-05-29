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
@Table(name="Tipo_talla")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Tipo_talla {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tipo_talla_id;

    @Column(nullable = false, length=10)
    private String descripcion;

}

//XS (Extra Small)
//S (Small)
//M (Medium)
//L (Large)
//XL (Extra Large)