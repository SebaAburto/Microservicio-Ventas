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
        Tipo_estado nuevo = tipo_estadoService.save(tipo_estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}
