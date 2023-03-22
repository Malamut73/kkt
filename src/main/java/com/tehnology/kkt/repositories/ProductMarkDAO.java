package com.tehnology.kkt.repositories;


import com.tehnology.kkt.models.catalog.ProductMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMarkDAO extends JpaRepository<ProductMark, Long> {
}
