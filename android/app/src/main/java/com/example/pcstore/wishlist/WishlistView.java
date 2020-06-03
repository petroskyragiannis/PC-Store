package com.example.pcstore.wishlist;

import com.example.pcstore.model.Client;

public interface WishlistView {
    void returnWishlist(Client client);
    void showStatus(String msg);
}
