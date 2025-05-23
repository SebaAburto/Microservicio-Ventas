package com.carrito.carrito.repository;

import com.venta.venta.model.Tipo_estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tipo_estadoRepository extends JpaRepository<Tipo_estado, Long> {
}
