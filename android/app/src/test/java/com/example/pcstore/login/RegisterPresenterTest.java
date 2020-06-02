package com.example.pcstore.login;

import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.UserDAOMemory;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterPresenterTest {

    RegisterPresenter presenter;
    RegisterViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new RegisterViewStub();
        presenter = new RegisterPresenter();
        presenter.setView(view);
        presenter.setUserDAO(new UserDAOMemory());
    }

    @Test
    public void existingUsernameRegister() {
        Client client = presenter.register("petraros", "password");
        assertNull(client);
        assertEquals("A user already exists with this username.", view.getStatus());
    }

    @Test
    public void shortPasswordRegister() {
        Client client = presenter.register("username2", "123");
        assertNull(client);
        assertEquals("Password must be at least 8 characters.", view.getStatus());
    }

    @Test
    public void guestRegister() {
        Client client = presenter.register("guest", "password");
        assertNull(client);
        assertEquals("Cannot register with this username.", view.getStatus());
    }

    @Test
    public void successfulRegister() {
        Client client = presenter.register("username", "password");
        assertEquals(new Client("username","password"), client);
        assertEquals("username registered successfully.", view.getStatus());
    }

    @Test
    public void failLogin() {
        User user = presenter.login("failUsername", "password");
        assertNull(user);
        assertEquals("No user found.", view.getStatus());
    }

    @Test
    public void successfulClientLogin() {
        User user = presenter.login("petraros", "petraros");
        UserDAOMemory dao = new UserDAOMemory();
        assertEquals(dao.find("petraros"), user);
        assertEquals("petraros logged in successfully.", view.getStatus());
    }

    @Test
    public void successfulEmployeeLogin() {
        User user = presenter.login("petrospetras", "petrospetras");
        UserDAOMemory dao = new UserDAOMemory();
        assertEquals(dao.find("petrospetras"), user);
        assertEquals("Employee petrospetras logged in successfully.", view.getStatus());
    }

    @Test
    public void wrongPasswordLogin() {
        User user = presenter.login("petraros", "password");
        assertNull(user);
        assertEquals("Wrong password.", view.getStatus());
    }

    @Test
    public void guestLogin() {
        Client client = presenter.loginAsGuest();
        assertEquals(new Client("guest", "guest"), client);
        assertEquals("Logged in as guest.", view.getStatus());
    }

    @Test
    public void returnCredentials() {
        presenter.returnCredentials("username", "password");
        assertEquals("username", view.getUsername());
        assertEquals("password", view.getPassword());
    }

}