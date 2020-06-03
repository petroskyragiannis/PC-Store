package com.example.pcstore.order;

import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.OrderDAOMemory;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.memorydao.UserDAOMemory;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Delivery;
import com.example.pcstore.model.Order;
import com.example.pcstore.model.Payment;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class OrderPresenterTest {

    OrderPresenter presenter;
    OrderViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new OrderViewStub();
        presenter = new OrderPresenter();
        presenter.setView(view);
        presenter.setOrderDAO(new OrderDAOMemory());
    }

    @Test
    public void createOrder() {
        UserDAOMemory userDAO = new UserDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Order order = presenter.createOrder(client);
        assertNotNull(order);
        assertEquals(client, order.getClient());
    }

    @Test
    public void successfulCompleteOrder() {
        OrderDAOMemory orderDAO = new OrderDAOMemory();
        Order order = orderDAO.find(1);
        boolean flag = presenter.completeOrder(order);
        assertTrue(flag);
    }

    @Test
    public void completeOrderMissingPersonalInformation() {
        Client client = new Client("username", "password");
        Order order = presenter.createOrder(client);
        boolean flag = presenter.completeOrder(order);
        assertFalse(flag);
        assertEquals("Missing personal information.", view.getStatus());
    }

    @Test
    public void completeOrderMissingDeliveryAddress() {
        UserDAOMemory userDAO = new UserDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        client.setAddress(null);

        Order order = presenter.createOrder(client);
        order.setPaymentMethod(Payment.CASH);
        order.setDeliveryMethod(Delivery.ADDRESS);

        boolean flag = presenter.completeOrder(order);

        assertFalse(flag);
        assertEquals("Missing delivery address.", view.getStatus());
    }

    @Test
    public void completeOrderMissingCardInformation() {
        UserDAOMemory userDAO = new UserDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        client.setCard(null);

        Order order = presenter.createOrder(client);
        order.setPaymentMethod(Payment.CARD);
        order.setDeliveryMethod(Delivery.STORE);

        boolean flag = presenter.completeOrder(order);

        assertFalse(flag);
        assertEquals("Missing card information.", view.getStatus());
    }


    @Test
    public void selectPaymentMethod() {
        UserDAOMemory userDAO = new UserDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Order order = new Order(4, client, client.getCart(), false, new GregorianCalendar(), null, null);
        presenter.onItemSelected(order, Payment.CASH);
        assertEquals(Payment.CASH, order.getPaymentMethod());
    }

    @Test
    public void selectDeliveryMethod() {
        UserDAOMemory userDAO = new UserDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Order order = new Order(4, client, client.getCart(), false, new GregorianCalendar(), null, null);
        presenter.onItemSelected(order, Delivery.STORE);
        assertEquals(Delivery.STORE, order.getDeliveryMethod());
    }

}