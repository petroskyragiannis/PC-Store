package com.example.pcstore.cart;

public interface CartItemSelectionListener<OrderLine> {
    void onItemSelected(OrderLine orderLine);
    void onItemSelected(OrderLine itemAtPosition, int quantity);
}
