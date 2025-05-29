package com.venta.venta.controller;

import com.venta.venta.model.Carrito;
import com.venta.venta.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> listar() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }

    @PostMapping
    public ResponseEntity<Carrito> guardar(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscar(@PathVariable Long id) {
        try {
            Carrito carrito = carritoService.findById(id);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long id, @RequestBody Carrito carrito) {
        try {
            Carrito carritoExistente = carritoService.findById(id);
            carritoExistente.setCantidad(carrito.getCantidad());
            carritoExistente.setFecha_modificacion(carrito.getFecha_modificacion());
            carritoExistente.setUsuario(carrito.getUsuario());
            carritoExistente.setTipo_estado(carrito.getTipo_estado());
            carritoExistente.setProductos(carrito.getProductos());

            carritoService.save(carritoExistente);
            return ResponseEntity.ok(carritoExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            carritoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

