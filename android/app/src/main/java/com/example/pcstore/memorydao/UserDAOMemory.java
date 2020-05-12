package com.example.pcstore.memorydao;

import com.example.pcstore.dao.UserDAO;
import com.example.pcstore.model.User;

import java.util.List;

public class UserDAOMemory implements UserDAO {
    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User find(String username) {
        return null;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
