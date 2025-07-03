package com.venta.venta.controller;

import com.venta.venta.assembler.UsuarioModelAssembler;
import com.venta.venta.model.Usuario;
import com.venta.venta.Service.UsuarioService;
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
@RequestMapping("/api/v1/usuariosV2")
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> buscar(@PathVariable Integer id) throws Exception {
        Usuario usuario = usuarioService.findById(id);
        return assembler.toModel(usuario);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> guardar(@RequestBody Usuario usuario) throws Exception {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity
                .created(linkTo(UsuarioControllerV2.class).slash(nuevoUsuario.getUsuario_id()).toUri())
                .body(assembler.toModel(nuevoUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) throws Exception {
        Usuario usuarioExistente = usuarioService.findById(id);

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setAppaterno(usuario.getAppaterno());
        usuarioExistente.setApmaterno(usuario.getApmaterno());
        usuarioExistente.setRun(usuario.getRun());
        usuarioExistente.setCorreo(usuario.getCorreo());
        usuarioExistente.setFecha_registro(usuario.getFecha_registro());

        Usuario actualizado = usuarioService.save(usuarioExistente);

        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/run/{run}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> buscarPorRun(@PathVariable String run) {
        List<EntityModel<Usuario>> usuarios = usuarioService.buscarPorRun(run).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).buscarPorRun(run)).withSelfRel());
    }
}
