package com.venta.venta.assembler;

import com.venta.venta.controller.CarritoControllerV2;
import com.venta.venta.model.Carrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoControllerV2.class).buscar(carrito.getCarrito_id().longValue())).withSelfRel(),
                linkTo(methodOn(CarritoControllerV2.class).listar()).withRel("carritos")
        );
    }
}