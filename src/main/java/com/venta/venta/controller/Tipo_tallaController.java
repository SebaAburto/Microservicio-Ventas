package com.venta.venta.controller;

import com.venta.venta.model.Tipo_talla;
import com.venta.venta.service.Tipo_tallaService;
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
        Tipo_talla nueva = tipo_tallaService.save(tipo_talla);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
}
