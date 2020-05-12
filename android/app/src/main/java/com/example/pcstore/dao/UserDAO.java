package com.example.pcstore.dao;

import com.example.pcstore.model.User;
import java.util.List;

public interface UserDAO {

    void save(User entity);

    void delete(User entity);

    List<User> findAll();

    User find(String username);

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);
}
