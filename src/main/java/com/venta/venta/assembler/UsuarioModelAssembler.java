package com.venta.venta.assembler;

import com.venta.venta.controller.UsuarioControllerV2;
import com.venta.venta.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        try {
            return EntityModel.of(usuario,
                    linkTo(methodOn(UsuarioControllerV2.class).buscar(usuario.getUsuario_id())).withSelfRel(),
                    linkTo(methodOn(UsuarioControllerV2.class).listar()).withRel("usuarios"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

