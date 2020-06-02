package com.example.pcstore.catalog;

public interface CatalogSelectionListener<Product> {
    void onItemSelectedCart(Product product);
    void onItemSelectedWishlist(Product product);
}
