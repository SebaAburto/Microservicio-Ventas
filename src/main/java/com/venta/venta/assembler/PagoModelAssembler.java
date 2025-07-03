package com.venta.venta.assembler;

import com.venta.venta.controller.PagoControllerV2;
import com.venta.venta.model.Pago;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>> {

    @Override
    public EntityModel<Pago> toModel(Pago pago) {
        return EntityModel.of(pago,
                linkTo(methodOn(PagoControllerV2.class).buscar(pago.getPago_id().longValue())).withSelfRel(),
                linkTo(methodOn(PagoControllerV2.class).listar()).withRel("pagos"));
    }
}
