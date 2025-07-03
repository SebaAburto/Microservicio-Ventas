package com.venta.venta.Repository;

import com.venta.venta.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(String categoria);

    long countByCategoria(String categoria);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
