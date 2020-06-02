package com.example.pcstore.catalog_update;

import com.example.pcstore.model.Employee;

public interface CatalogUpdateView {
    void returnEmployee(Employee employee);
    void showStatus(String msg);
}
