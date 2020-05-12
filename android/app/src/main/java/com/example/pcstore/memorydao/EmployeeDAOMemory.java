package com.example.pcstore.memorydao;

import com.example.pcstore.dao.EmployeeDAO;
import com.example.pcstore.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOMemory implements EmployeeDAO {
    protected static ArrayList<Employee> entities = new ArrayList<Employee>();

    @Override
    public void save(Employee entity) {
        entities.add(entity);
    }

    @Override
    public void delete(Employee entity) {
        entities.remove(entity);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<Employee>(entities);

    }

    @Override
    public Employee find(String username) {
        for (Employee employee: entities)
            if (employee.getUsername().equalsIgnoreCase(username))
                return employee;
        return null;
    }

    @Override
    public Employee findByPhoneNumber(String phoneNumber) {
        for (Employee employee: entities)
            if (employee.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                return employee;
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        for (Employee employee: entities)
            if (employee.getEmail().equalsIgnoreCase(email))
                return employee;
        return null;
    }
}
