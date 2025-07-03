package com.venta.venta.controller;

import com.venta.venta.assembler.ProductoModelAssembler;
import com.venta.venta.model.Producto;
import com.venta.venta.Service.ProductoService;
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
@RequestMapping("/api/v1/productosV2")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> listar() {
        List<EntityModel<Producto>> productos = productoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Producto> buscar(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return assembler.toModel(producto);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> guardar(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoController.class).buscar(nuevoProducto.getProducto_id().longValue())).toUri())
                .body(assembler.toModel(nuevoProducto));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoExistente = productoService.findById(id);

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setColor(producto.getColor());
        productoExistente.setFecha_creacion(producto.getFecha_creacion());

        Producto actualizado = productoService.save(productoExistente);

        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Búsqueda por categoría
    @GetMapping(value = "/categoria/{categoria}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        List<EntityModel<Producto>> productos = productoService.findByCategoria(categoria).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControllerV2.class).buscarPorCategoria(categoria)).withSelfRel());
    }

    @GetMapping(value = "/nombre/{nombre}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> buscarPorNombre(@PathVariable String nombre) {
    List<EntityModel<Producto>> productos = productoService.findByNombre(nombre).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(productos,
            linkTo(methodOn(ProductoControllerV2.class).buscarPorNombre(nombre)).withSelfRel());
    }
}
