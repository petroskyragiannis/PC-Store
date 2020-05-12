package com.example.pcstore.dao;

import com.example.pcstore.model.Component;
import java.util.List;

public interface ComponentDAO {

    void save(Component entity);

    void delete(Component entity);

    int nextId();

    List<Component> findAll();

    Component find(String id);

    Component findByName(String name);
}