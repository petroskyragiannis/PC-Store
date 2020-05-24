package com.example.pcstore;

import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartPresenter {
    private CartView view;
    private List<OrderLine> list;

    public CartPresenter() {
        list = new ArrayList<>();
    }

    public void setupList(Set<OrderLine> set) {
        list.addAll(set);
    }

    public void onItemSelected(Client client, OrderLine orderLine) {
        client.removeFromCart(orderLine);
        list.remove(orderLine);
    }

    public void onItemSelected(OrderLine orderLine, int quantity) {
        if (orderLine.getStock() < quantity)
            view.showStatus("Not enough stock.");
        else orderLine.setQuantity(quantity);
    }

    public void returnCart() {
        view.returnCart();
    }

    public List<OrderLine> getList() {
        return list;
    }

    public void setList(List<OrderLine> list) {
        this.list = list;
    }

    public void setView(CartView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }
}
