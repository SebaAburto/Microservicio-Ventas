package com.ventas.ventas.repository;

import com.Microservicio-Ventas.cl.Microservicio-Ventas.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
