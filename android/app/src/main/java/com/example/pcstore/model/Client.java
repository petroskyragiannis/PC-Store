package com.example.pcstore.model;

import java.util.HashSet;
import java.util.Set;

public class Client extends User {

    private Address address;
    private CardInfo card;
    private Set<OrderLine> cart;
    private Set<Product> wishlist;

    //Constructors
    public Client(String username, String password) {
        super(username, password);
        cart = new HashSet<>();
        wishlist = new HashSet<>();
    }

    public Client(String username, String password, String name, String surname, String phoneNumber, String email, Address address, CardInfo card) {
        super(username, password, name, surname, phoneNumber, email);
        this.address = address;
        this.card = card;
        cart = new HashSet<>();
        wishlist = new HashSet<>();
    }

    // Cart Methods
    public void addToCart(OrderLine orderLine) {
        cart.add(orderLine);
    }

    public void removeFromCart(OrderLine orderLine) {
        cart.remove(orderLine);
    }

    //Wishlist Methods
    public void addToWishlist(Product p) {
        wishlist.add(p);
    }

    public void removeFromWishlist(Product p) {
        if (!wishlist.isEmpty())
            wishlist.remove(p);
    }

    //Getters and Setters
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CardInfo getCard() {
        return card;
    }

    public void setCard(CardInfo card) {
        this.card = card;
    }

    public Set<OrderLine> getCart() {
        return cart;
    }

    public void setCart(Set<OrderLine> cart) {
        this.cart = cart;
    }

    public Set<Product> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Product> wishlist) {
        this.wishlist = wishlist;
    }

}