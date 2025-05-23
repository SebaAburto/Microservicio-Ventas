package com.carrito.carrito.repository;

import com.Microservicio-Carrito.cl.Microservicio-Carrito.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
