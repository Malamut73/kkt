package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.ProductEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEquipmentDAO extends JpaRepository<ProductEquipment, Long> {
}
