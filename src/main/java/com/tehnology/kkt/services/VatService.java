package com.tehnology.kkt.services;

import com.tehnology.kkt.models.catalog.Vat;
import com.tehnology.kkt.repositories.VatDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatService {

    private final VatDAO vatDAO;

    public List<Vat> findAll() {
        return vatDAO.findAll();
    }

    public void save(Vat vat) {
        vatDAO.save(vat);
    }

    public void delete(Vat vat) {
        vatDAO.delete(vat);
    }
}
