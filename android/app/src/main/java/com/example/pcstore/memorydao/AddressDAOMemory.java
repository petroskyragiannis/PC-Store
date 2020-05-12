package com.example.pcstore.memorydao;

import com.example.pcstore.dao.AddressDAO;
import com.example.pcstore.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressDAOMemory implements AddressDAO {
    protected static ArrayList<Address> entities = new ArrayList<Address>();

    @Override
    public void save(Address entity) {
        entities.add(entity);
    }

    @Override
    public void delete(Address entity) {
        entities.remove(entity);
    }

    @Override
    public List<Address> findAll() {
        ArrayList<Address> result = new ArrayList<Address>();
        result.addAll(entities);
        return result;
    }
}
