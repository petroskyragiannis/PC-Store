package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOMemory implements ProductDAO {
    protected static ArrayList<Product> entities = new ArrayList<Product>();

    @Override
    public void save(Product entity) {
        entities.add(entity);
    }

    @Override
    public void delete(Product entity) {
        entities.remove(entity);
    }

    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<Product>(entities);
    }

    @Override
    public Product find(int id) {
        for (Product product: entities)
            if (product.getId()==id)
                return product;
        return null;
    }

    @Override
    public Product findByName(String name) {
        for (Product product: entities)
            if (product.getName().equalsIgnoreCase(name))
                return product;
        return null;
    }
}
