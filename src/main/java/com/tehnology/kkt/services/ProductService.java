package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.repositories.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;


    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public List<Product> findByUser(User user) {

        List<Product> products = productDAO.findAll();

        return null;
    }

    public Product findById(Long id) {
            return productDAO.getReferenceById(id);
    }

    public Product findLastOFD(User user) {
        Date ofdResult = null;
        Date ofdCurrent = null;

        Product lastOfdProduct = null;

        for (Product product :
                user.getProducts()) {
            if(ofdResult == null){
                ofdResult = product.getOfd().getDateStart();
            }
            ofdCurrent = product.getOfd().getDateStart();
            if(ofdResult.before(ofdCurrent)){
                ofdResult = ofdCurrent;
                lastOfdProduct = product;
            }

        }

        return lastOfdProduct;
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }
}
