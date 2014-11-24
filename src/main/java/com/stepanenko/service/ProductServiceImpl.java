package com.stepanenko.service;

import com.stepanenko.DAO.ProductDAO;
import com.stepanenko.logic.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public void addProduct(Product p) {
        this.productDAO.addProduct(p);
    }

    @Override
    @Transactional
    public List<Product> search(String searchString) {
        return this.productDAO.search(searchString);
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return this.productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public void deleteProduct(int good_id) {
        this.productDAO.deleteProduct(good_id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
       this.productDAO.updateProduct(product);
    }
}
