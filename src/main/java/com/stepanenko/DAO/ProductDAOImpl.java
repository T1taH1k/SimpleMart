package com.stepanenko.DAO;

import com.stepanenko.logic.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addProduct(Product p) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(p);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> search(String searchString) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select * from products where name like ?").addEntity(Product.class);
        List<Product> productList = query.setString(0, "%" + searchString + "%").list();
        return productList;
    }


    @Override
    public List<Product> getAllProducts() {

        Session session = sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("from Product").list();
        return productList;
    }

    @Override
    public void deleteProduct(int good_id) {

        Session session = sessionFactory.getCurrentSession();
        Product p = (Product) session.load(Product.class, new Integer(good_id));
        if(null != p){
            session.delete(p);
        }

    }

    @Override
    public void updateProduct(Product p) {
        Session session = sessionFactory.getCurrentSession();
        session.update(p);
    }

    @Override
    public void clear(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Product");
        query.executeUpdate();

    }

}
