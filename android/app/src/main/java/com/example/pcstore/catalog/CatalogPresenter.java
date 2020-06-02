package com.example.pcstore.catalog;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CatalogPresenter {

    private CatalogView view;
    private ProductDAO productDAO;

    public CatalogPresenter() {}

    public List<Product> getCatalog() {
        return productDAO.findAll();
    }

    public boolean onItemSelectedCart(Client client, Product product) {
        SimpleOrderLine simpleOrderLine = new SimpleOrderLine(product);
        if (!contains(client,simpleOrderLine)) {
            client.addToCart(simpleOrderLine);
            deleteProduct(product);
            simpleOrderLine.updateStock();
            saveProduct(product);
            return true;
        }
        return false;
    }

    public void onItemSelectedWishlist(Client client, Product product) {
        if (client.getWishlist().contains(product)) return;
        client.addToWishlist(product);
        view.showStatus(product.getName() + " added to wishlist.");
    }

    public boolean onPcConfigurationSelected(Client client, PcConfiguration pcConfiguration) {
        if (!contains(client, pcConfiguration)) {
            client.addToCart(pcConfiguration);
            for (Component c : pcConfiguration.getComponents())
                if (c != null) deleteProduct(c);
            pcConfiguration.updateStock();
            for (Component c : pcConfiguration.getComponents())
                if (c != null) saveProduct(c);
            view.showStatus("Custom PC Configuration added to cart.");
            return true;
        }
        return false;
    }

    public void onWishlistSelected(Client client, Set<Product> wishlist) {
        client.setWishlist(wishlist);
    }

    public void onCartSelected(Client client, Set<OrderLine> cart) {
        client.setCart(cart);
    }

    public void signOutClient(Client client) {
        view.returnClient(client);
    }

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public void deleteProduct(Product product) {
        productDAO.delete(product);
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

    private boolean contains(Client client, OrderLine orderLine) {
        if (orderLine instanceof SimpleOrderLine) {
            SimpleOrderLine simpleOrderLine = (SimpleOrderLine) orderLine;
            for (OrderLine o: client.getCart()) {
                if (o instanceof SimpleOrderLine) {
                    SimpleOrderLine s = (SimpleOrderLine) o;
                    if (simpleOrderLine.getProduct().equals(s.getProduct()))
                        return true;
                }
            }
        } else if (orderLine instanceof PcConfiguration) {
            PcConfiguration pcConfiguration = (PcConfiguration) orderLine;
            for (OrderLine o: client.getCart()) {
                if (o instanceof PcConfiguration) {
                    PcConfiguration pc = (PcConfiguration) o;
                    if (Arrays.equals(pcConfiguration.getComponents(), pc.getComponents()))
                        return true;
                }
            }
        }
        return false;
    }

}