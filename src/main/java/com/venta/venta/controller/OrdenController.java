package com.venta.venta.controller;

import com.venta.venta.model.Orden;
import com.venta.venta.service.OrdenService;
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
}
