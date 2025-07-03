package com.venta.venta.controller;

import com.venta.venta.model.Producto;
import com.venta.venta.Service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setProducto_id(1);
        producto.setNombre("Camisa");
        producto.setDescripcion("Camisa de algodón");
        producto.setPrecio(19.99);
        producto.setCategoria("Ropa");
        producto.setColor("Blanco");
    }

    @Test
    public void testListarProductos() throws Exception {
        when(productoService.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/v1/productos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producto_id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Camisa"));
    }

    @Test
    public void testBuscarProductoPorId() throws Exception {
        when(productoService.findById(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto_id").value(1))
                .andExpect(jsonPath("$.nombre").value("Camisa"));
    }

    @Test
    public void testGuardarProducto() throws Exception {
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.producto_id").value(1))
                .andExpect(jsonPath("$.nombre").value("Camisa"));
    }

    @Test
    public void testActualizarProducto() throws Exception {
        when(productoService.findById(1L)).thenReturn(producto);
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/v1/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto_id").value(1))
                .andExpect(jsonPath("$.nombre").value("Camisa"));
    }

    @Test
    public void testEliminarProducto() throws Exception {
        doNothing().when(productoService).delete(1L);

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isNoContent());
    }
}
