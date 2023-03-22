package com.tehnology.kkt.services;

import com.tehnology.kkt.models.catalog.Taxation;
import com.tehnology.kkt.repositories.TaxationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxationService {

    private final TaxationDAO taxationDAO;

    public List<Taxation> findAll() {
        return taxationDAO.findAll();
    }

    public void save(Taxation taxation) {
        taxationDAO.save(taxation);
    }

    public Taxation findById(Long id) {
        return taxationDAO.getReferenceById(id);
    }

    public void delete(Taxation taxation) {
        taxationDAO.delete(taxation);
    }
}
