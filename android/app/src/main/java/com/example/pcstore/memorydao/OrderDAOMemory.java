package com.example.pcstore.memorydao;

import com.example.pcstore.dao.OrderDAO;
import com.example.pcstore.model.Order;

import java.util.List;

public class OrderDAOMemory implements OrderDAO {
    @Override
    public void save(Order entity) {

    }

    @Override
    public void delete(Order entity) {

    }

    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAllPending() {
        return null;
    }

    @Override
    public Order find(String id) {
        return null;
    }
}
