package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.repositories.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;


    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public Product findById(Long id) {
            return productDAO.getReferenceById(id);
    }

    public Product findProductByLastOFDDate(User user) {
         Product product = new ArrayList<>(user.getProducts())
                 .stream().min(Comparator
                 .comparing(date -> date.getOfd().getDateStart())).orElse(null);
        return product;
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }


    public void deleteProduct(Product product) {
        productDAO.delete(product);
    }
}
