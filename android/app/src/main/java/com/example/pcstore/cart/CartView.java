package com.example.pcstore.cart;

import com.example.pcstore.model.Client;

public interface CartView {
    void returnCart(Client client);
    void showStatus(String msg);
}
