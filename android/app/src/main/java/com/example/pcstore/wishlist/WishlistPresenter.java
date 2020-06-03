package com.example.pcstore.wishlist;

import com.example.pcstore.model.Client;
import com.example.pcstore.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WishlistPresenter {

    private WishlistView view;

    public WishlistPresenter() {}

    public List<Product> getList(Set<Product> set) {
        List<Product> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    public void onItemSelected(Client client, Product product) {
        client.removeFromWishlist(product);
    }

    public void returnWishlist(Client client) {
        view.returnWishlist(client);
    }

    public void setView(WishlistView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

}
