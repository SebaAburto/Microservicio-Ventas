package com.venta.venta.service;

import com.venta.venta.Service.ProductoService;
import com.venta.venta.model.Producto;
import com.venta.venta.Repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService ProductoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        when(productoRepository.findAll()).thenReturn(List.of(
                new Producto(1, "Camisa", "Algodón", 19.99, "Ropa", "Blanco", null, null)
        ));

        List<Producto> productos = ProductoService.findAll();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testFindById() {
        Producto producto = new Producto(1, "Camisa", "Algodón", 19.99, "Ropa", "Blanco", null, null);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto found = ProductoService.findById(1L);
        assertNotNull(found);
        assertEquals(1, found.getProducto_id());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto(1, "Camisa", "Algodón", 19.99, "Ropa", "Blanco", null, null);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = ProductoService.save(producto);
        assertNotNull(saved);
        assertEquals("Camisa", saved.getNombre());
    }

    @Test
    public void testDelete() {
        doNothing().when(productoRepository).deleteById(1L);

        ProductoService.delete(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}