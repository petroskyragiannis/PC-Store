package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ProductTest {

    Product p1,p2;
    Set<Product> set1 = new HashSet<>();
    Set<Product> set2 = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        p1 = new Product("1","pc1",10,1500,"PC");
        p2 = new Product("2", "pc2",5,1000,"PC");
        set1.add(p1);
        set1.add(p2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCatalog() {
        assertEquals(Product.getCatalog(), set1);
    }

    @Test
    public void removeFromCatalog() {
        Product.removeFromCatalog(p1);
        Product.removeFromCatalog(p2);
        assertEquals(Product.getCatalog(), set2);
    }

    @Test
    public void setStock() {
        p2.setStock(-5);
        assertEquals(p2.getStock(),5);
    }
}