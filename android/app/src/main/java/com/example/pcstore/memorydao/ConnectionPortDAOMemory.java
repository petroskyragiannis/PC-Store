package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ConnectionPortDAO;
import com.example.pcstore.model.ComponentType;
import com.example.pcstore.model.ConnectionPort;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPortDAOMemory implements ConnectionPortDAO {
    protected static ArrayList<ConnectionPort> entities = new ArrayList<ConnectionPort>();

    @Override
    public void save(ConnectionPort entity) {
        entities.add(entity);
    }

    @Override
    public void delete(ConnectionPort entity) {
        entities.remove(entity);
    }

    @Override
    public List<ConnectionPort> findAll() {
        return new ArrayList<ConnectionPort>(entities);

    }

    @Override
    public ConnectionPort find(String name) {
        for (ConnectionPort connectionPort: entities)
            if (connectionPort.getName().equalsIgnoreCase(name))
                return connectionPort;
        return null;
    }
}
