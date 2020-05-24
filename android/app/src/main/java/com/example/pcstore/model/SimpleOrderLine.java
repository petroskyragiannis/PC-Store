package com.example.pcstore.model;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.Objects;

public class SimpleOrderLine extends OrderLine {
    private Product product;

    // Constructors
    public SimpleOrderLine(Product product) {
        super(1);
        this.product = product;
    }

    @Override
    public int getSubTotal() {
        return product.getPrice()*quantity;
    }

    @Override
    public void updateStock() {
        product.setStock(product.getStock()-quantity);
    }

    @Override
    public int getStock() {
        return product.getStock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleOrderLine that = (SimpleOrderLine) o;
        return product.equals(that.product);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
