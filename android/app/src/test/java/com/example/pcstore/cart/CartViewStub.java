package com.example.pcstore.cart;

import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;

import java.util.Set;

public class CartViewStub implements CartView {

    Set<OrderLine> cart;
    String status;

    @Override
    public void returnCart(Client client) {
        cart = client.getCart();
    }

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public Set<OrderLine> getCart() {
        return cart;
    }

    public String getStatus() {
        return status;
    }

}