package com.example.pcstore.dao;

import com.example.pcstore.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    void save(Employee entity);

    void delete(Employee entity);

    List<Employee> findAll();

    Employee find(String username);

    Employee findByPhoneNumber(String phoneNumber);

    Employee findByEmail(String email);

}
