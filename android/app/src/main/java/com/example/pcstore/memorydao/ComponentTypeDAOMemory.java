package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ComponentTypeDAO;
import com.example.pcstore.model.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class ComponentTypeDAOMemory implements ComponentTypeDAO {
    protected static ArrayList<ComponentType> entities = new ArrayList<ComponentType>();

    @Override
    public void save(ComponentType entity) {
        entities.add(entity);
    }

    @Override
    public void delete(ComponentType entity) {
        entities.remove(entity);
    }

    @Override
    public List<ComponentType> findAll() {
        return new ArrayList<ComponentType>(entities);

    }

    @Override
    public ComponentType find(String name) {
        for (ComponentType componentType: entities)
            if (componentType.getName().equalsIgnoreCase(name))
                return componentType;
        return null;
    }
}
