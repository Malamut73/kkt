package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.Taxation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxationDAO extends JpaRepository<Taxation, Long> {
}
