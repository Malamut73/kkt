package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.catalog.TypeOfActivity;
import com.tehnology.kkt.repositories.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

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

    public Product findByNumber(String number) {
        return productDAO.findByNumber(number);
    }

    public List<Product> findByUser(User user) {
        return productDAO.findByUser(user);
    }

    public void setTypeOfActivity(Product product, String[] typeofactivities, Principal principal) {
        Set<String> typeOfActivities = new HashSet<>(Arrays.asList(typeofactivities));
        product.setTypeOfActivities(typeOfActivities);
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил вид деятельности").build());
        productDAO.save(product);
    }


    public void setProductMark(String[] marks, Principal principal, Product product) {
        Set<String> productMarks = new HashSet<>(Arrays.asList(marks));
        product.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил маркировку").build());
        product.setProductMark(productMarks);
        productDAO.save(product);
    }

    public Product saveAndflush(Product product) {
        return productDAO.saveAndFlush(product);
    }

    public Product SvaAndFlush(Product product) {
        return productDAO.saveAndFlush(product);
    }
}
