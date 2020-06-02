package com.example.pcstore.catalog_update;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Employee;
import com.example.pcstore.model.Product;
import java.util.List;

public class CatalogUpdatePresenter {

    private CatalogUpdateView view;
    private ProductDAO productDAO;

    public CatalogUpdatePresenter() {}

    public List<Product> getCatalog() {
        return productDAO.findAll();
    }

    public void saveProduct(Product product) {
            productDAO.save(product);
    }

    public void deleteProduct(Product product) {
        productDAO.delete(product);
    }

    public void signOutEmployee(Employee employee) {
        view.returnEmployee(employee);
    }

    public void setView(CatalogUpdateView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}