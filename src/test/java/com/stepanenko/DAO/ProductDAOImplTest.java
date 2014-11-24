package com.stepanenko.DAO;

import com.stepanenko.logic.Product;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-test.xml")
@TransactionConfiguration
@Transactional
public class ProductDAOImplTest {

    public static final String PRODUCTNAME1 = "productname1";
    public static final String PRODUCTNAME2 = "productname2";

    @Autowired
    ProductDAO productDAO;

    @Before
    public void clear() {
        productDAO.clear();
    }

    @org.junit.Test
    public void testAddProduct() throws Exception {
        productDAO.addProduct(new Product(PRODUCTNAME1, new BigDecimal(10)));
        List<Product> allProducts = productDAO.getAllProducts();
        Assert.assertTrue(allProducts.size()==1);
        Product product = allProducts.get(0);
        Assert.assertEquals(PRODUCTNAME1, product.getName());

    }

    @org.junit.Test
    public void testSearch() throws Exception {
        productDAO.addProduct(new Product(PRODUCTNAME1, new BigDecimal(10)));
        List<Product> searchList = productDAO.search(PRODUCTNAME1);
        Product product = searchList.get(0);
        Assert.assertEquals(PRODUCTNAME1, product.getName());

    }

    @org.junit.Test
    public void testGetAllProducts() throws Exception {
        productDAO.addProduct(new Product(PRODUCTNAME1, new BigDecimal(10)));
        productDAO.addProduct(new Product(PRODUCTNAME2, new BigDecimal(20)));
        List<Product> allProducts = productDAO.getAllProducts();
        Product product1 = allProducts.get(0);
        Assert.assertEquals(PRODUCTNAME1, product1.getName());
        Product product2 = allProducts.get(1);
        Assert.assertEquals(PRODUCTNAME2, product2.getName());

    }

    @org.junit.Test
    public void testDeleteProduct() throws Exception {
        productDAO.addProduct(new Product(PRODUCTNAME1, new BigDecimal(10)));
        List<Product> allProducts = productDAO.getAllProducts();
        Product product1 = allProducts.get(0);
        productDAO.deleteProduct(product1.getGood_id());
        allProducts = productDAO.getAllProducts();
        Assert.assertFalse(allProducts.size() == 1);

    }
}