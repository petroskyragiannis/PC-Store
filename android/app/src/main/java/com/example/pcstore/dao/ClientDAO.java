package com.example.pcstore.dao;

import com.example.pcstore.model.Client;
import java.util.List;

public interface ClientDAO {

        void save(Client entity);

        void delete(Client entity);

        List<Client> findAll();

        Client find(String username);

        Client findByPhoneNumber(String phoneNumber);

        Client findByEmail(String email);

}