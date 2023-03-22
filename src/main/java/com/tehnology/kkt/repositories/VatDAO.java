package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.Vat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VatDAO extends JpaRepository<Vat, Long> {
}
