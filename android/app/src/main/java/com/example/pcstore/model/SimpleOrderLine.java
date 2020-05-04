package com.example.pcstore.model;

public class SimpleOrderLine extends OrderLine {

    private Product product;
    private int quantity;

    // Constructors
    public SimpleOrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleOrderLine)) return false;

        SimpleOrderLine that = (SimpleOrderLine) o;

        if (quantity != that.quantity) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
