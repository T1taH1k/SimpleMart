package com.stepanenko.logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="good_id")
    private int good_id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    public Product() {
        name = null;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }


    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public int getGood_id() { return good_id; }

    public void setGood_id(int good_id) { this.good_id = good_id; }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }


}

