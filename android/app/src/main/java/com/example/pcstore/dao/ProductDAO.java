package com.example.pcstore.dao;

import com.example.pcstore.model.Product;
import java.util.List;

public interface ProductDAO {

    void save(Product entity);

    void delete(Product entity);

    int nextId();

    List<Product> findAll();

    Product find(String id);

    Product findByName(String name);

}
