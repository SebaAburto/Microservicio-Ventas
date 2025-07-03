package com.venta.venta.Repository;

import com.venta.venta.model.Pago;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p WHERE p.metodo_pago = :metodoPago")
    List<Pago> buscarPorMetodoPago(@Param("metodoPago") String metodoPago);

    @Query("SELECT p FROM Pago p WHERE p.estado_pago = :estadoPago")
    List<Pago> buscarPorEstadoPago(@Param("estadoPago") String estadoPago);

}
