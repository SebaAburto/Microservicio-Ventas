package com.venta.venta.controller;

import com.venta.venta.model.Transaccion;
import com.venta.venta.Service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<List<Transaccion>> listar() {
        List<Transaccion> transacciones = transaccionService.findAll();
        if (transacciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transacciones);
    }

    @PostMapping
    public ResponseEntity<Transaccion> guardar(@RequestBody Transaccion transaccion) {
        Transaccion nueva = transaccionService.save(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
}
