package com.ventas.ventas.repository;

import com.Microservicio-Ventas.cl.Microservicio-Ventas.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
