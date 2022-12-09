package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
}
