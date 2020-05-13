package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {

    private int id;
    private Client client;
    private Set<OrderLine> orderLines;
    private boolean completed;
    private Calendar orderDate;
    private Delivery deliveryMethod;
    private Payment paymentMethod;

    // Constructors

    public Order(Client client, Set<OrderLine> orderLines) {
        this.client = client;
        this.orderLines = orderLines;
    }

    public Order(int id, Client client, Set<OrderLine> orderLines, boolean completed, Calendar orderDate, Delivery deliveryMethod, Payment paymentMethod) {
        this.id = id;
        this.client = client;
        this.orderLines = orderLines;
        this.completed = completed;
        this.orderDate = orderDate;
        this.deliveryMethod = deliveryMethod;
        this.paymentMethod = paymentMethod;
    }

    public void updateStock() {
        for (OrderLine orderLine: orderLines) orderLine.updateStock();
    }

    // OrderLine Methods
    public void addOrderLine(OrderLine o) {
        orderLines.add(o);
    }

    public void removeOrderLine(OrderLine o) {
        orderLines.remove(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                completed == order.completed &&
                client.equals(order.client) &&
                orderLines.equals(order.orderLines) &&
                orderDate.equals(order.orderDate) &&
                deliveryMethod == order.deliveryMethod &&
                paymentMethod == order.paymentMethod;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, client, orderLines, completed, orderDate, deliveryMethod, paymentMethod);
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public Delivery getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Delivery deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotal() {
        int total=0;
        for (OrderLine orderLine: orderLines) {
            total += orderLine.getSubTotal();
        }
        return total;
    }

    //TODO Android
    /*
    public static int getMonthlyReport(int month) {
        int monthlyIncome = 0;
        for (Order order: orders) {
            if (order.getOrderDate().get(Calendar.MONTH)==month) {
                monthlyIncome += order.getTotal();
            }
        }
        return monthlyIncome;
    }

     */
}