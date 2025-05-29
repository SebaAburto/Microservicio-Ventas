package com.venta.venta.controller;

import com.venta.venta.model.Tipo_talla;
import com.venta.venta.Service.Tipo_tallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-talla")
public class Tipo_tallaController {

    @Autowired
    private Tipo_tallaService tipo_tallaService;

    @GetMapping
    public ResponseEntity<List<Tipo_talla>> listar() {
        List<Tipo_talla> tallas = tipo_tallaService.findAll();
        if (tallas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tallas);
    }

    @PostMapping
    public ResponseEntity<Tipo_talla> guardar(@RequestBody Tipo_talla tipo_talla) {
        Tipo_talla nuevaTalla = tipo_tallaService.save(tipo_talla);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTalla);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo_talla> buscar(@PathVariable Long id) {
    try {
        Tipo_talla talla = tipo_tallaService.findById(id);
        return ResponseEntity.ok(talla);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo_talla> actualizar(@PathVariable Long id, @RequestBody Tipo_talla tipo_talla) {
    try {
        Tipo_talla tallaExistente = tipo_tallaService.findById(id);
        tallaExistente.setTipo_talla_id(tipo_talla.getTipo_talla_id());
        tipo_tallaService.save(tallaExistente);
        return ResponseEntity.ok(tallaExistente);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    try {
        tipo_tallaService.delete(id);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }
}