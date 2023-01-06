package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.firdirectory.Tariff;
import com.tehnology.kkt.repositories.TariffDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffService {

    private final TariffDAO tariffDAO;

    public List<Tariff> findAll() {
        return tariffDAO.findAll();
    }

    public void save(Tariff tariff) {
        tariffDAO.save(tariff);
    }

    public Tariff findById(Long id) {
        return tariffDAO.getReferenceById(id);
    }
}
