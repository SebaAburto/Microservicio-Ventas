package com.venta.venta.controller;

import com.venta.venta.assembler.PagoModelAssembler;
import com.venta.venta.model.Carrito;
import com.venta.venta.model.Pago;
import com.venta.venta.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/pagosV2")
public class PagoControllerV2 {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pago>> listar() {
        List<EntityModel<Pago>> pagos = pagoService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pagos, linkTo(methodOn(PagoController.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pago> buscar(@PathVariable Long id) {
        Pago pago = pagoService.findById(id);
        return assembler.toModel(pago);
    }

    @GetMapping("/metodo/{metodoPago}")
    public CollectionModel<EntityModel<Pago>> buscarPorMetodoPago(@PathVariable String metodoPago) {
        List<EntityModel<Pago>> pagos = pagoService.buscarPorMetodoPago(metodoPago).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pagos);
    }

    @GetMapping("/estado/{estadoPago}")
    public CollectionModel<EntityModel<Pago>> buscarPorEstadoPago(@PathVariable String estadoPago) {
        List<EntityModel<Pago>> pagos = pagoService.buscarPorEstadoPago(estadoPago).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pagos);
    }
}