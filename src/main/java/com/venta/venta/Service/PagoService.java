package com.venta.venta.Service;

import com.venta.venta.model.Pago;
import com.venta.venta.model.Producto;
import com.venta.venta.model.Orden;
import com.venta.venta.Repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Pago findById(Long id) {
        return pagoRepository.findById(id).get();
    }

    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void delete(Long id) {
        pagoRepository.deleteById(id);
    }
    
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    public List<Pago> buscarPorMetodoPago(String metodoPago) {
        return pagoRepository.buscarPorMetodoPago(metodoPago);
    }

    public List<Pago> buscarPorEstadoPago(String estadoPago) {
        return pagoRepository.buscarPorEstadoPago(estadoPago);
    }
}
