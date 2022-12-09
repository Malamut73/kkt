package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.repositories.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final ProductDAO productDAO;


    public void saveProduct(Product product) {
        productDAO.save(product);
    }
}
