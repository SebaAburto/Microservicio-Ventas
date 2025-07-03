package com.venta.venta.controller;

import com.venta.venta.assembler.CarritoModelAssembler;
import com.venta.venta.model.Carrito;
import com.venta.venta.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/carritosV2")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Carrito>> listar() {
        List<EntityModel<Carrito>> carritos = carritoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(carritos,
                linkTo(methodOn(CarritoControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Carrito> buscar(@PathVariable Long id) {
        Carrito carrito = carritoService.findById(id);
        return assembler.toModel(carrito);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> guardar(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.save(carrito);
        return ResponseEntity
                .created(linkTo(methodOn(CarritoControllerV2.class).buscar(nuevoCarrito.getCarrito_id().longValue())).toUri())
                .body(assembler.toModel(nuevoCarrito));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> actualizar(@PathVariable Long id, @RequestBody Carrito carrito) {
        Carrito carritoExistente = carritoService.findById(id);

        carritoExistente.setCantidad(carrito.getCantidad());
        carritoExistente.setFecha_modificacion(carrito.getFecha_modificacion());
        carritoExistente.setUsuario(carrito.getUsuario());
        carritoExistente.setTipo_estado(carrito.getTipo_estado());
        carritoExistente.setProducto(carrito.getProducto());

        Carrito actualizado = carritoService.save(carritoExistente);

        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        carritoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipoEstado/{id}")
    public List<Carrito> obtenerPorTipoEstadoId(@PathVariable Integer id) {
        return carritoService.buscarPorTipoEstadoId(id);
    }
}