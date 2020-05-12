package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Product;

import java.util.List;

public class ProductDAOMemory implements ProductDAO {
    @Override
    public void save(Product entity) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product find(String id) {
        return null;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }
}
