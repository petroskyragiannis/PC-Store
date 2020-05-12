package com.example.pcstore.memorydao;

import com.example.pcstore.dao.OrderDAO;
import com.example.pcstore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOMemory implements OrderDAO {
    protected static ArrayList<Order> entities = new ArrayList<Order>();

    @Override
    public void save(Order entity) {
        entities.add(entity);
    }

    @Override
    public void delete(Order entity) {
        entities.remove(entity);
    }

    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);

    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<Order>(entities);
    }

    @Override
    public List<Order> findAllPending() {
        List<Order> allOrders = findAll();
        List<Order> pending = new ArrayList<Order>();
        for (Order order : allOrders)
            if (!order.isCompleted())
                pending.add(order);
        return pending;
    }

    @Override
    public Order find(int id) {
        for (Order order: entities)
            if (order.getId()==id)
                return order;
        return null;
    }
}
