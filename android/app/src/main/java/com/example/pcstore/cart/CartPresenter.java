package com.example.pcstore.cart;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;
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
        //orderLine.restoreStock();
    }

    public void onItemSelected(OrderLine orderLine, int quantity) {
        if (quantity == 0) return;
        //orderLine.restoreStock();
        if (quantity > orderLine.getStock()) {
            view.showStatus("Not enough stock.");
        }
        else {
            orderLine.setQuantity(quantity);
            //ForderLine.updateStock();
        }
    }

    public void returnCart() {
        view.returnCart();
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
