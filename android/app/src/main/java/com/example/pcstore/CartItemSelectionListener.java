package com.example.pcstore;

public interface CartItemSelectionListener<OrderLine> {
    void onCartItemSelected(OrderLine orderLine);
    void onCartItemSelected(OrderLine itemAtPosition, int quantity);
}
