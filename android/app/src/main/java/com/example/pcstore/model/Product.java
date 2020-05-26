package com.example.pcstore.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Product implements Serializable {

    private int id;
    private String name;
    private int stock;
    private int price;
    private Hardware category;

    // Constructors
    public Product(int id, String name, int stock, int price, Hardware category) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (stock != product.stock) return false;
        if (price != product.price) return false;
        if (id != product.id) return false;
        if (!name.equalsIgnoreCase(product.name)) return false;
        return category.equals(product.category);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + stock;
        result = 31 * result + price;
        result = 31 * result + category.hashCode();
        return result;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0) this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hardware getCategory() {
        return category;
    }

    public void setCategory(Hardware category) {
        this.category = category;
    }

}