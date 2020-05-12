package com.example.pcstore.dao;

import com.example.pcstore.model.Order;

import java.util.List;

public interface OrderDAO {

    void save(Order entity);

    void delete(Order entity);

    int nextId();

    List<Order> findAll();

    List<Order> findAllPending();

    Order find(int id);

}
