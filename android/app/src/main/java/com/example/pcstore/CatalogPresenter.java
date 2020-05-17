package com.example.pcstore;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Product;
import java.util.List;

public class CatalogPresenter {

    private CatalogView view;
    private ProductDAO productDAO;

    public CatalogPresenter() {}

    public List<Product> getCatalog() {
        return productDAO.findAll();
    }

    public void onProductSelected(Product product){
        view.addToCart(product);
        view.showStatus(product.getName() + " added to cart.");
    }

    public void setView(CatalogView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
