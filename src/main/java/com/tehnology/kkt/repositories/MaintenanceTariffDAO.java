package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.MaintenanceTariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTariffDAO extends JpaRepository<MaintenanceTariff, Long> {
    MaintenanceTariff findByName(String name);
}
