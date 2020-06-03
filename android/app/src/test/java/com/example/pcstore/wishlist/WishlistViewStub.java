package com.example.pcstore.wishlist;

import com.example.pcstore.model.Client;
import com.example.pcstore.model.Product;

import java.util.Set;

public class WishlistViewStub implements WishlistView {

    Set<Product> wishlist;
    String status;

    @Override
    public void returnWishlist(Client client) {
        wishlist = client.getWishlist();
    }

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public Set<Product> getWishlist() {
        return wishlist;
    }

    public String getStatus() {
        return status;
    }

}