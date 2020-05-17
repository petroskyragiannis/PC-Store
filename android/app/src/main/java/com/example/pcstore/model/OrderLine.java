package com.example.pcstore.model;

import java.io.Serializable;

public abstract class OrderLine implements Serializable {

    public abstract int getSubTotal();

    public abstract void updateStock();

}
