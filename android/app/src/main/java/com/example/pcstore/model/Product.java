package com.example.pcstore.model;

import java.util.HashSet;
import java.util.Set;

public class Product {

    private int id;
    private String name;
    private int stock;
    private int price;
    private String category;

    // Constructors
    public Product(int id, String name, int stock, int price, String category) {
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
        return category.equalsIgnoreCase(product.category);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /* TODO Android
    public static void printCatalog() {
        Set<String> categories = new HashSet<>();
        for (Product product: catalog) {
            if (!categories.contains(product.getCategory())) {
                System.out.println(product.getCategory());
                categories.add(product.getCategory());
            }
        }
    }


    public static void printCatalog(String category) {
        if (category.equalsIgnoreCase("Component")) {
            Set<String> categories = new HashSet<>();
            for (Product product: catalog) {
                if (product.getCategory().equalsIgnoreCase("Component")) {
                    Component component = (Component) product;
                    String type = component.getType().getName();
                    if (!categories.contains(type)) {
                        System.out.println(type);
                        categories.add(type);
                    }
                }
            }
        } else {
            for (Product product: catalog) {
                if (product.getCategory().equalsIgnoreCase(category) && product.getStock()>0)
                        System.out.println(product.getName());
            }
        }
    }

    public static void printComponents(String type) {
        for (Product product: catalog) {
            if (product.getCategory().equalsIgnoreCase("Components")) {
                Component component = (Component) product;
                String componentType = component.getType().getName();
                if (componentType.equalsIgnoreCase(type) && product.getStock()>0)
                        System.out.println(component.getName());
            }
        }
    }
    */

}