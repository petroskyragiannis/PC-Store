package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ComponentDAO;
import com.example.pcstore.model.Component;

import java.util.ArrayList;
import java.util.List;

public class ComponentDAOMemory implements ComponentDAO {
    protected static ArrayList<Component> entities = new ArrayList<Component>();

    @Override
    public void save(Component entity) {
        entities.add(entity);
    }

    @Override
    public void delete(Component entity) {
        entities.remove(entity);
    }

    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }

    @Override
    public List<Component> findAll() {
        return new ArrayList<Component>(entities);
    }

    @Override
    public Component find(int id) {
        for (Component component: entities)
            if (component.getId()==id)
                return component;
        return null;
    }

    @Override
    public Component findByName(String name) {
        for (Component component: entities)
            if (component.getName().equalsIgnoreCase(name))
                return component;
        return null;
    }
}
