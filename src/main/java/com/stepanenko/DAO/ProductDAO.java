package com.stepanenko.DAO;

import com.stepanenko.logic.Product;

import java.util.List;

public interface ProductDAO {

    public void addProduct(Product product);

    public List<Product> search(String searchString);

    public List<Product> getAllProducts();

    public void deleteProduct(int good_id);

    public void updateProduct(Product product);

    public void clear();
}
