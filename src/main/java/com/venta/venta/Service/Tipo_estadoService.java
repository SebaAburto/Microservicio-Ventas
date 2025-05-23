package com.venta.venta.service;

import com.venta.venta.model.Tipo_estado;
import com.venta.venta.repository.Tipo_estadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class Tipo_estadoService {

    @Autowired
    private Tipo_estadoRepository tipo_estadoRepository;

    public List<Tipo_estado> findAll() {
        return tipo_estadoRepository.findAll();
    }

    public Tipo_estado findById(Long id) {
        return tipo_estadoRepository.findById(id).get();
    }

    public Tipo_estado save(Tipo_estado tipo_estado) {
        return tipo_estadoRepository.save(tipo_estado);
    }

    public void delete(Long id) {
        tipo_estadoRepository.deleteById(id);
    }
}
