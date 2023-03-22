package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {
    Product findByNumber(String number);

    List<Product> findByUser(User user);
}
