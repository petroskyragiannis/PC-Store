package com.example.pcstore.cart;

import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.PcConfigurationDAOMemory;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.memorydao.UserDAOMemory;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class CartPresenterTest {

    CartPresenter presenter;
    CartViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new CartViewStub();
        presenter = new CartPresenter();
        presenter.setView(view);
        presenter.setProductDAO(new ProductDAOMemory());
    }

    @Test
    public void removeProductFromCart() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        SimpleOrderLine simpleOrderLine = new SimpleOrderLine(product);
        client.addToCart(simpleOrderLine);
        simpleOrderLine.updateStock();
        presenter.onItemSelected(client, simpleOrderLine);
        assertEquals(10, product.getStock());
    }

    @Test
    public void removeConfigurationFromCart() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("AMD Ryzen 5 3600");
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        client.addToCart(pc);
        pc.updateStock();
        presenter.onItemSelected(client, pc);
        for (Component c: pc.getComponents()) assertEquals(10, c.getStock());
    }

    @Test
    public void updateProductQuantity() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        SimpleOrderLine simpleOrderLine = new SimpleOrderLine(product);
        client.addToCart(simpleOrderLine);
        simpleOrderLine.updateStock();
        boolean flag = presenter.onItemSelected(simpleOrderLine, 8);
        assertTrue(flag);
        assertEquals(8, simpleOrderLine.getQuantity());
        assertEquals(2, product.getStock());
    }

    @Test
    public void updateConfigurationQuantity() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("AMD Ryzen 5 3600");
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        client.addToCart(pc);
        pc.updateStock();
        boolean flag = presenter.onItemSelected(pc, 4);
        assertTrue(flag);
        assertEquals(4, pc.getQuantity());
        for (Component c: pc.getComponents()) assertEquals(6, c.getStock());
    }

    @Test
    public void failUpdateQuantity() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        SimpleOrderLine simpleOrderLine = new SimpleOrderLine(product);
        client.addToCart(simpleOrderLine);
        boolean flag = presenter.onItemSelected(simpleOrderLine, 15);
        assertFalse(flag);
        assertEquals("Not enough stock.", view.getStatus());
    }

    @Test
    public void returnCart() {
        UserDAOMemory userDAO = new UserDAOMemory();

        Client client = (Client) userDAO.find("Boubas");
        presenter.returnCart(client);
        assertEquals(new HashSet<OrderLine>(), view.getCart());
        assertEquals(client.getCart(), view.getCart());
    }

}