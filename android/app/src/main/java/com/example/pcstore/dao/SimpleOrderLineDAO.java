package com.example.pcstore.dao;

import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;
import java.util.List;

public interface SimpleOrderLineDAO {

    void save(SimpleOrderLine entity);

    void delete(SimpleOrderLine entity);

    List<SimpleOrderLine> findAll();

    SimpleOrderLine find(Product product, int quantity);
}
