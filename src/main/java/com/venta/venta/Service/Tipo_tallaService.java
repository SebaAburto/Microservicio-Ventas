package com.venta.venta.Service;

import com.venta.venta.model.Tipo_talla;
import com.venta.venta.Repository.Tipo_tallaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class Tipo_tallaService {

    @Autowired
    private Tipo_tallaRepository tipo_tallaRepository;

    public List<Tipo_talla> findAll() {
        return tipo_tallaRepository.findAll();
    }

    public Tipo_talla findById(Long id) {
        return tipo_tallaRepository.findById(id).get();
    }

    public Tipo_talla save(Tipo_talla tipo_talla) {
        return tipo_tallaRepository.save(tipo_talla);
    }

    public void delete(Long id) {
        tipo_tallaRepository.deleteById(id);
    }
}
