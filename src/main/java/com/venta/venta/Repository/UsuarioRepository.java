package com.carrito.carrito.repository;

import com.Microservicio-Carrito.cl.Microservicio-Carrito.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
