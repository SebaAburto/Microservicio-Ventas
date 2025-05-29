package com.venta.venta.controller;

import com.venta.venta.model.Tipo_estado;
import com.venta.venta.Service.Tipo_estadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-estados")
public class Tipo_estadoController {

    @Autowired
    private Tipo_estadoService tipo_estadoService;

    @GetMapping
    public ResponseEntity<List<Tipo_estado>> listar() {
        List<Tipo_estado> estados = tipo_estadoService.findAll();
        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    @PostMapping
    public ResponseEntity<Tipo_estado> guardar(@RequestBody Tipo_estado tipo_estado) {
        Tipo_estado nuevoEstado = tipo_estadoService.save(tipo_estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo_estado> buscar(@PathVariable Long id) {
    try {
        Tipo_estado estado = tipo_estadoService.findById(id);
        return ResponseEntity.ok(estado);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @PutMapping("/{id}")
        public ResponseEntity<Tipo_estado> actualizar(@PathVariable Long id, @RequestBody Tipo_estado tipo_estado) {
    try {
        Tipo_estado estadoExistente = tipo_estadoService.findById(id);
        estadoExistente.setTipo_estado_id(tipo_estado.getTipo_estado_id());
        tipo_estadoService.save(estadoExistente);
        return ResponseEntity.ok(estadoExistente);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    try {
        tipo_estadoService.delete(id);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }
}
