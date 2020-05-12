package com.example.pcstore.dao;

import com.example.pcstore.model.ConnectionPort;
import java.util.List;

public interface ConnectionPortDAO {

    void save(ConnectionPort entity);

    void delete(ConnectionPort entity);

    List<ConnectionPort> findAll();

    ConnectionPort find(String name);
}
