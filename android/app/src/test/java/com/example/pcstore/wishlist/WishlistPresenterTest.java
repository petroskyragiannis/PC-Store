package com.example.pcstore.wishlist;


import com.example.pcstore.catalog.CatalogPresenter;
import com.example.pcstore.catalog.CatalogViewStub;
import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.memorydao.UserDAOMemory;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WishlistPresenterTest {

    WishlistPresenter presenter;
    WishlistViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new WishlistViewStub();
        presenter = new WishlistPresenter();
        presenter.setView(view);
    }


    @Test
    public void removeFromWishlist() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        client.addToWishlist(product);
        presenter.onItemSelected(client, product);
        assertEquals(0, client.getWishlist().size());
    }

    @Test
    public void returnWishlist() {
        UserDAOMemory userDAO = new UserDAOMemory();
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Client client = (Client) userDAO.find("Boubas");
        Product product = productDAO.findByName("NZXT H510");
        client.addToWishlist(product);
        presenter.returnWishlist(client);
        assertEquals(client.getWishlist(), view.getWishlist());
    }


}