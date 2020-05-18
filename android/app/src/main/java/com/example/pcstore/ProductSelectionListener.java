package com.example.pcstore;

public interface ProductSelectionListener<Product> {
    void onProductSelectedCart(Product product);
    void onProductSelectedWishlist(Product product);
}
