package com.tehnology.kkt.services;

import com.tehnology.kkt.models.catalog.ProductMark;
import com.tehnology.kkt.repositories.ProductMarkDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMarkService {

    private final ProductMarkDAO productMarkDAO;

    public List<ProductMark> findAll() {
        return productMarkDAO.findAll();
    }

    public void delete(ProductMark productMark) {
        productMarkDAO.delete(productMark);
    }

    public void save(ProductMark productMark) {
        productMarkDAO.save(productMark);
    }


}
