package com.inventario.inventario.repository;

import com.Microservicio-Inventario.cl.Microservicio-Inventario.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
