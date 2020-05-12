package com.example.pcstore.dao;

import com.example.pcstore.model.Address;

import java.util.List;

public interface AddressDAO {

    void save(Address entity);

    void delete(Address entity);

    List<Address> findAll();

}
    /*
    save
    delete
    find
    findAll
    findbySomething
    nextId
     */
