package com.stepanenko.service;

import com.stepanenko.logic.Product;

import java.util.List;

public interface ProductService {

    public void addProduct(Product product);

    public List<Product> search(String searchString);

    public List<Product> getAllProducts();

    public void deleteProduct(int id);

    public void updateProduct(Product product);

}
