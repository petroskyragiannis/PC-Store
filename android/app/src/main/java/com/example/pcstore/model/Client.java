package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Client extends User {

    private Address address;
    private CardInfo card;
    private Set<OrderLine> cart = new HashSet<>();
    private Set<Product> wishlist = new HashSet<>();

    //Constructors
    public Client(String username, String password) {
        super(username, password);
    }

    public Client(String username, String password, String name, String surname, String phoneNumber, String email, Address address, CardInfo card) {
        super(username, password, name, surname, phoneNumber, email);
        this.address = address;
        this.card = card;
    }

    // Cart Methods
    public void addToCart(Product product, int quantity){
        if (quantity > product.getStock()) {
            //throw new OutOfStockException("There is not enough quantity of this product");
            return;
        }
        if (cart.size() != 0) {
            for (OrderLine o : cart) {
                if (o instanceof SimpleOrderLine) {
                    SimpleOrderLine s = (SimpleOrderLine) o;
                    if (s.getProduct().equals(product)) {
                        s.setQuantity(s.getQuantity() + quantity);
                        return;
                    }
                }
            }
        }
        OrderLine orderLine = new SimpleOrderLine(product, quantity);
        cart.add(orderLine);
    }

    public void addToCart(PcConfiguration config) {
        if (config!=null) cart.add(config);
    }

    public void removeFromCart(Product product, int quantity) {
        if (cart.size()==0) return;
        for (OrderLine orderLine: cart) {
            if (orderLine instanceof SimpleOrderLine) {
                SimpleOrderLine s = (SimpleOrderLine) orderLine;
                if (s.getProduct().equals(product)) {
                    if (s.getQuantity()<=quantity) cart.remove(s);
                    else s.setQuantity(s.getQuantity()-quantity);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void removeFromCart(PcConfiguration config) {
        if (cart.size()==0) return;
        for (OrderLine orderLine: cart) {
            if (orderLine instanceof PcConfiguration) {
                PcConfiguration pc = (PcConfiguration) orderLine;
                if(pc.equals(config)) {
                    cart.remove(pc);
                    return;
                }
            }
        }
    }

    //TODO Android
    public Order createOrder() {
        Order order = new Order(this, cart);
        cart = new HashSet<>();
        // setDeliveryAddress(...);
        // setPaymentMethod(...);
        order.updateStock();
        // SendEmail(...)
        return order;
    }

    //Wishlist Methods
    public void addToWishlist(Product p) {
        wishlist.add(p);
    }

    public void removeFromWishlist(Product p) {
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

    public static class OutOfStockException extends Exception {
        public OutOfStockException(String message) {
            super(message);
        }
    }

}