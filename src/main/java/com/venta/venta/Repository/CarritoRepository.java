package com.venta.venta.Repository;

import com.venta.venta.model.Carrito;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

    @Repository
    public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    @Query("SELECT c FROM Carrito c WHERE c.tipo_estado.tipo_estado_id = :id")
    List<Carrito> buscarPorTipoEstadoId(@Param("id") Integer id);

}
