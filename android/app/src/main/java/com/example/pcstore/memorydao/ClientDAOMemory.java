package com.example.pcstore.memorydao;

import com.example.pcstore.dao.ClientDAO;
import com.example.pcstore.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAOMemory implements ClientDAO {
    protected static ArrayList<Client> entities = new ArrayList<Client>();

    @Override
    public void save(Client entity) {
        entities.add(entity);
    }

    @Override
    public void delete(Client entity) {
        entities.remove(entity);
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<Client>(entities);
    }

    @Override
    public Client find(String username) {
        for (Client client: entities)
            if (client.getUsername().equalsIgnoreCase(username))
                return client;
        return null;
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        for (Client client: entities)
            if (client.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                return client;
        return null;
    }

    @Override
    public Client findByEmail(String email) {
        for (Client client: entities)
            if (client.getEmail().equalsIgnoreCase(email))
                return client;
        return null;
    }
}
