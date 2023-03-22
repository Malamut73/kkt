package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.catalog.ProductEquipment;
import com.tehnology.kkt.repositories.ProductEquipmentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductEquipmentService {

    private final ProductEquipmentDAO productEquipmentDAO;

    public List<ProductEquipment> findAll() {
        return productEquipmentDAO.findAll();
    }

    public void save(ProductEquipment productEquipment) {
        productEquipmentDAO.save(productEquipment);
    }

    public void delete(ProductEquipment productEquipment) {
        productEquipmentDAO.delete(productEquipment);
    }

}
