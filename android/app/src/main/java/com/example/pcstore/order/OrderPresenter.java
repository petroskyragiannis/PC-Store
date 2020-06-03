package com.example.pcstore.order;

import com.example.pcstore.dao.OrderDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Delivery;
import com.example.pcstore.model.Order;
import com.example.pcstore.model.Payment;

import java.util.GregorianCalendar;

public class OrderPresenter {

    private OrderView view;
    private OrderDAO orderDAO;

    public Order createOrder(Client client) {
        return new Order(orderDAO.nextId(), client, client.getCart(), false,
                new GregorianCalendar(), null, null);
    }

    public boolean completeOrder(Order order) {
        if (order.getClient().getName() == ""|| order.getClient().getSurname() == null
        || order.getClient().getPhoneNumber() == null || order.getClient().getEmail() == null ) {
            view.showStatus("Missing personal information.");
            return false;
        }
        if (order.getDeliveryMethod()== Delivery.ADDRESS) {
            if (order.getClient().getAddress() == null) {
                view.showStatus("Missing delivery address.");
                return false;
            }
        }
        else if (order.getPaymentMethod() == Payment.CARD) {
                if (order.getClient().getCard() == null) {
                    view.showStatus("Missing card information.");
                    return false;
                }
            }
        orderDAO.save(order);
        view.showStatus("Order created.");
        return true;
    }

    public void onItemSelected(Order order, Payment payment) {
        order.setPaymentMethod(payment);
    }

    public void onItemSelected(Order order, Delivery delivery) {
        order.setDeliveryMethod(delivery);
    }

    public void setView(OrderView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

}