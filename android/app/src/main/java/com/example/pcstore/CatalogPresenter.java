package com.example.pcstore;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import java.util.List;
import java.util.Set;

public class CatalogPresenter {

    private CatalogView view;
    private ProductDAO productDAO;

    public CatalogPresenter() {}

    public List<Product> getCatalog() {
        return productDAO.findAll();
    }

    public void onProductSelectedCart(Client client, Product product) {
        client.addToCart(product, 1);
        view.showStatus(product.getName() + " added to cart.");
    }

    public void onProductSelectedWishlist(Client client, Product product) {
        if (client.getWishlist().contains(product))
            view.showStatus(product.getName() + " is already in your wishlist.");
        else {
            client.addToWishlist(product);
            view.showStatus(product.getName() + " added to wishlist.");
        }
    }

    public void onPcConfigurationSelected(Client client, PcConfiguration pcConfiguration) {
        client.addToCart(pcConfiguration);

        view.showStatus("Custom PC Configuration added to cart.");
    }

    public void updateWishlist(Client client, Set<Product> wishlist) {
        client.setWishlist(wishlist);
    }

    public void setView(CatalogView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

}
