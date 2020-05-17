package com.example.pcstore;

import com.example.pcstore.model.Product;

public interface CatalogView {
    void addToCart(Product product);
    void showStatus(String msg);
}
