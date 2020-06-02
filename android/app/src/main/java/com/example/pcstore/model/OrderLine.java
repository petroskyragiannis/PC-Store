package com.example.pcstore.model;

import java.io.Serializable;

public abstract class OrderLine implements Serializable {
    int quantity;

    public OrderLine(int quantity) {
        this.quantity = quantity;
    }

    public abstract int getSubTotal();

    public abstract void updateStock();

    public abstract void restoreStock();

    public abstract int getStock();

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
