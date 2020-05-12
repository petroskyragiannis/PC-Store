package com.example.pcstore.dao;

import com.example.pcstore.model.ComponentType;
import java.util.List;

public interface ComponentTypeDAO {

    void save(ComponentType entity);

    void delete(ComponentType entity);

    List<ComponentType> findAll();

    ComponentType find(String name);
}
