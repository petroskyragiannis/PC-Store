package com.example.pcstore;

import com.example.pcstore.model.Client;
import com.example.pcstore.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WishlistPresenter {

    private WishlistView view;
    private List<Product> list;

    public WishlistPresenter() {
        list = new ArrayList<>();
    }

    public void setupList(Set<Product> set) {
        list.addAll(set);
    }

    public void onItemSelected(Client client, Product product) {
        client.removeFromWishlist(product);
        list.remove(product);
    }

    public void returnWishlist() {
        view.returnWishlist();
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public void setView(WishlistView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

}
