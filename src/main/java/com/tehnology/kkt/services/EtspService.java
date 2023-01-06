package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.firdirectory.Etsp;
import com.tehnology.kkt.repositories.EtspDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtspService {

    private final EtspDAO etspDAO;

    public List<Etsp> findAll() {
        return etspDAO.findAll();
    }

    public void save(Etsp etsp) {
        etspDAO.save(etsp);
    }

    public Etsp findById(Long id) {
        return etspDAO.getReferenceById(id);
    }
}
