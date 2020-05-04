package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {

    private static int counterId = 0;
    private int id;
    private Client client;
    private Set<OrderLine> orderLines;
    private int total;
    private boolean completed;
    private Calendar orderDate;
    private Address deliveryAddress;
    private String paymentMethod;
    private static Set<Order> orders = new HashSet<>();

    // Constructors
    public Order(Client client, Set<OrderLine> orderLines) {
        counterId++;
        this.id = counterId;
        this.client = client;
        this.orderLines = orderLines;
        this.orderDate = new GregorianCalendar();
        this.completed = false;
        this.total = getTotal();
        orders.add(this);
    }

    public void updateStock() {
        for (OrderLine orderLine: orderLines) orderLine.updateStock();
    }

    //TODO Android
    public static int getMonthlyReport(int month) {
        int monthlyIncome = 0;
        for (Order order: orders) {
            if (order.getOrderDate().get(Calendar.MONTH)==month) {
                monthlyIncome += order.getTotal();
            }
        }
        return monthlyIncome;
    }

    // OrderLine Methods
    public void addOrderLine(OrderLine o) {
        orderLines.add(o);
    }

    public void removeOrderLine(OrderLine o) {
        orderLines.remove(o);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (completed != order.completed) return false;
        if (total != order.total) return false;
        if (!client.equals(order.client)) return false;
        if (!orderLines.equals(order.orderLines)) return false;
        if (!orderDate.equals(order.orderDate)) return false;
        if (!Objects.equals(deliveryAddress, order.deliveryAddress)) return false;
        return Objects.equals(paymentMethod, order.paymentMethod);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + client.hashCode();
        result = 31 * result + orderLines.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + total;
        return result;
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

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotal() {
        int total=0;
        for (OrderLine orderLine: orderLines) {
            total += orderLine.getSubTotal();
        }
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static Set<Order> getOrders() {
        return orders;
    }

    public static void setOrders(Set<Order> orders) {
        Order.orders = orders;
    }

}