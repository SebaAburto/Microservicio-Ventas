package com.venta.venta.controller;

import com.venta.venta.model.Pago;
import com.venta.venta.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        List<Pago> pagos = pagoService.findAll();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @PostMapping
    public ResponseEntity<Pago> guardar(@RequestBody Pago pago) {
        Pago nuevo = pagoService.save(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscar(@PathVariable Long id) {
    try {
        Pago pago = pagoService.findById(id);
        return ResponseEntity.ok(pago);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
    try {
        Pago existente = pagoService.findById(id);
        existente.setEstado_pago(pago.getEstado_pago());

        pagoService.save(existente);
        return ResponseEntity.ok(existente);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    try {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
    }
}
