package com.example.pcstore.memorydao;

import com.example.pcstore.dao.UserDAO;
import com.example.pcstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOMemory implements UserDAO {
    protected static ArrayList<User> entities = new ArrayList<User>();

    @Override
    public void save(User entity) {
        entities.add(entity);
    }

    @Override
    public void delete(User entity) {
        entities.remove(entity);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(entities);
    }

    @Override
    public User find(String username) {
        for (User user: entities)
            if (user.getUsername().equalsIgnoreCase(username))
                return user;
        return null;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        for (User user: entities)
            if (user.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                return user;
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for (User user: entities)
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        return null;
    }
}
