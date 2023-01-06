package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.firdirectory.MaintenanceTariff;
import com.tehnology.kkt.repositories.MaintenanceTariffDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceTariffService {

    private final MaintenanceTariffDAO maintenanceTariffDAO;

    public List<MaintenanceTariff> findAll() {
        return maintenanceTariffDAO.findAll();
    }

    public void save(MaintenanceTariff maintenanceTariff) {
        maintenanceTariffDAO.save(maintenanceTariff);
    }

    public MaintenanceTariff findById(Long id) {
        return maintenanceTariffDAO.getReferenceById(id);
    }

    public MaintenanceTariff findByName(String name) {
        return maintenanceTariffDAO.findByName(name);
    }
}
