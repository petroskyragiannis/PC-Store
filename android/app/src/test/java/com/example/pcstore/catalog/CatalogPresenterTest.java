package com.example.pcstore.catalog;

import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.PcConfigurationDAOMemory;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.memorydao.UserDAOMemory;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CatalogPresenterTest {

    CatalogPresenter presenter;
    CatalogViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new CatalogViewStub();
        presenter = new CatalogPresenter();
        presenter.setView(view);
        presenter.setProductDAO(new ProductDAOMemory());
    }

    @Test
    public void successfulAddToCart() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        boolean flag = presenter.onItemSelectedCart(client, product);
        assertTrue(flag);
        assertEquals(9, product.getStock());
        assertEquals(1, client.getCart().size());
    }

    @Test
    public void addExistingToCart() {
        UserDAOMemory userDAO = new UserDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Product product = productDAO.findByName("NZXT H510");
        client.addToCart(new SimpleOrderLine(product));
        boolean flag = presenter.onItemSelectedCart(client, product);
        assertFalse(flag);
    }

    @Test
    public void successfulAddToWishlist() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        boolean flag = presenter.onItemSelectedWishlist(client, product);
        assertTrue(flag);
        assertEquals(1, client.getWishlist().size());
        assertEquals("NZXT H510 added to wishlist.", view.getStatus());
    }

    @Test
    public void addExistingToWishlist() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        client.addToWishlist(product);
        boolean flag = presenter.onItemSelectedWishlist(client, product);
        assertFalse(flag);
    }

    @Test
    public void successfulAddPcConfiguration() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("AMD Ryzen 5 3600");
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        boolean flag = presenter.onPcConfigurationSelected(client, pc);
        assertTrue(flag);
        for (Component c: pc.getComponents()) assertEquals(9, c.getStock());
        assertEquals("Custom PC Configuration added to cart.", view.getStatus());

    }

    @Test
    public void addExistingPcConfiguration() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("AMD Ryzen 5 3600");
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        client.addToCart(pc);
        boolean flag = presenter.onPcConfigurationSelected(client, pc);
        assertFalse(flag);
    }

    @Test
    public void signOut() {
        UserDAOMemory userDAO = new UserDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        presenter.signOutClient(client);
        assertEquals(client, view.getClient());
    }

}