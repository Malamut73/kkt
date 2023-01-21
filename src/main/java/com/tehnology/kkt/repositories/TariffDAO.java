package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffDAO extends JpaRepository<Tariff, Long> {
}
