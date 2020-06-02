package com.example.pcstore.cart;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartPresenter {

    private CartView view;
    private ProductDAO productDAO;

    public CartPresenter() {}

    public List<OrderLine> getCartList(Set<OrderLine> cart) {
        List<OrderLine> cartList = new ArrayList<>();
        cartList.addAll(cart);
        return cartList;
    }

    public void onItemSelected(Client client, OrderLine orderLine) {
        client.removeFromCart(orderLine);
        if (orderLine instanceof SimpleOrderLine) {
            SimpleOrderLine simpleOrderLine = (SimpleOrderLine) orderLine;
            deleteProduct(simpleOrderLine.getProduct());
            simpleOrderLine.restoreStock();
            saveProduct(simpleOrderLine.getProduct());
        } else if (orderLine instanceof PcConfiguration) {
            PcConfiguration pcConfiguration = (PcConfiguration) orderLine;
            for (Component c : pcConfiguration.getComponents())
                if (c != null) deleteProduct(c);
            pcConfiguration.restoreStock();
            for (Component c : pcConfiguration.getComponents())
                if (c != null) saveProduct(c);
        }
    }

    public boolean onItemSelected(OrderLine orderLine, int quantity) {
        if (quantity <= 0 || quantity > orderLine.getStock() + orderLine.getQuantity()) {
            view.showStatus("Not enough stock.");
            return false;
        }
        if (orderLine instanceof SimpleOrderLine) {
            SimpleOrderLine simpleOrderLine = (SimpleOrderLine) orderLine;
            deleteProduct(simpleOrderLine.getProduct());
            simpleOrderLine.restoreStock();

            simpleOrderLine.setQuantity(quantity);
            simpleOrderLine.updateStock();
            saveProduct(simpleOrderLine.getProduct());
        } else if (orderLine instanceof PcConfiguration) {
            PcConfiguration pcConfiguration = (PcConfiguration) orderLine;
            for (Component c : pcConfiguration.getComponents())
                if (c != null) deleteProduct(c);
            pcConfiguration.restoreStock();
            pcConfiguration.setQuantity(quantity);
            pcConfiguration.updateStock();
            for (Component c : pcConfiguration.getComponents())
                if (c != null) saveProduct(c);
        }
        return true;
    }

    public void returnCart() {
        view.returnCart();
    }

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public void deleteProduct(Product product) {
        productDAO.delete(product);
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void setView(CartView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }
}
