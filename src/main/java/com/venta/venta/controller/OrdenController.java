package com.venta.venta.controller;

import com.venta.venta.model.Orden;
import com.venta.venta.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        List<Orden> ordenes = ordenService.findAll();
        if (ordenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ordenes);
    }

    @PostMapping
    public ResponseEntity<Orden> guardar(@RequestBody Orden orden) {
        Orden nueva = ordenService.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> buscar(@PathVariable Long id) {
    try {
        Orden orden = ordenService.findById(id);
        return ResponseEntity.ok(orden);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Long id, @RequestBody Orden orden) {
    try {
        Orden existente = ordenService.findById(id);
        existente.setFecha_creacion(orden.getFecha_creacion());
        existente.setUsuario(orden.getUsuario());
        existente.setCarrito(orden.getCarrito());

        ordenService.save(existente);
        return ResponseEntity.ok(existente);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    try {
        ordenService.delete(id);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }
}
