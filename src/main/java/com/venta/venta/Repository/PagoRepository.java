package com.ventas.ventas.repository;

import com.Microservicio-Ventas.cl.Microservicio-Ventas.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}
