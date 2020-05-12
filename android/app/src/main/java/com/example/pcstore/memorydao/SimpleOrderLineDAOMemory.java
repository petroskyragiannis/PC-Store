package com.example.pcstore.memorydao;

import com.example.pcstore.dao.SimpleOrderLineDAO;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;

import java.util.List;

public class SimpleOrderLineDAOMemory implements SimpleOrderLineDAO {
    @Override
    public void save(SimpleOrderLine entity) {

    }

    @Override
    public void delete(SimpleOrderLine entity) {

    }

    @Override
    public List<SimpleOrderLine> findAll() {
        return null;
    }

    @Override
    public SimpleOrderLine find(Product product, int quantity) {
        return null;
    }
}
