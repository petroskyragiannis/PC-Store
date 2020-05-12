package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ComponentDAO;
import com.example.pcstore.model.Component;

import java.util.List;

public class ComponentDAOMemory implements ComponentDAO {
    @Override
    public void save(Component entity) {

    }

    @Override
    public void delete(Component entity) {

    }

    @Override
    public int nextId() {
        return 0;
    }

    @Override
    public List<Component> findAll() {
        return null;
    }

    @Override
    public Component find(String id) {
        return null;
    }

    @Override
    public Component findByName(String name) {
        return null;
    }
}
