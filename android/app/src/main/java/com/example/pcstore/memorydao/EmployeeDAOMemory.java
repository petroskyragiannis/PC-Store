package com.example.pcstore.memorydao;

import com.example.pcstore.dao.EmployeeDAO;
import com.example.pcstore.model.Employee;

import java.util.List;

public class EmployeeDAOMemory implements EmployeeDAO {
    @Override
    public void save(Employee entity) {

    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee find(String username) {
        return null;
    }

    @Override
    public Employee findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        return null;
    }
}
