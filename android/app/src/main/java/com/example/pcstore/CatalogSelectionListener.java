package com.example.pcstore;

public interface CatalogSelectionListener<Product> {
    void onProductSelectedCart(Product product);
    void onProductSelectedWishlist(Product product);
}
