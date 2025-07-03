package com.venta.venta.Service;

import com.venta.venta.model.Carrito;
import com.venta.venta.model.Producto;
import com.venta.venta.model.Tipo_estado;
import com.venta.venta.model.Usuario;
import com.venta.venta.Repository.CarritoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Carrito findById(Long id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void delete(Long id) {
        carritoRepository.deleteById(id);
    }

    public List<Carrito> buscarPorTipoEstadoId(Integer tipoEstadoId) {
    return carritoRepository.buscarPorTipoEstadoId(tipoEstadoId);
    }
}

