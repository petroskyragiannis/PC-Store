package com.example.pcstore.memorydao;

import com.example.pcstore.dao.SimpleOrderLineDAO;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrderLineDAOMemory implements SimpleOrderLineDAO {
    protected static ArrayList<SimpleOrderLine> entities = new ArrayList<SimpleOrderLine>();

    @Override
    public void save(SimpleOrderLine entity) {
        entities.add(entity);

    }

    @Override
    public void delete(SimpleOrderLine entity) {
        entities.remove(entity);

    }

    @Override
    public List<SimpleOrderLine> findAll() {
        return new ArrayList<SimpleOrderLine>(entities);

    }

    @Override
    public SimpleOrderLine find(Product product, int quantity) {
        for (SimpleOrderLine simpleOrderLine: entities)
            if (simpleOrderLine.getProduct().equals(product) && simpleOrderLine.getQuantity()==quantity)
                return simpleOrderLine;
        return null;
    }
}
