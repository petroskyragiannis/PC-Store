package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserTest {

    Client c,c1,c2;
    Employee e1,e2;
    Set<User> set = new HashSet<>();
    Set<User> set2 = new HashSet<>();

    @Before
    public void setUp() throws Exception {

        c = new Client("client","clientclient");
        User.addToRegisteredUsers(c);
        c1 = new Client("client1","client1client1");
        c2 = new Client("client2","client1client2");
        e1 = new Employee("employee1", "employee1");
        e2 = new Employee("employee2", "employee2");

        set.add(c);
        set.add(c1);
        set.add(e1);
        set.add(e2);
        set2.add(c);
        set2.add(c1);
        set2.add(c2);
        set2.add(e1);
        set2.add(e2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void register() {
        Client register = User.register("client1","client1client1");
        assertEquals(c1,register);
        assertEquals(set,User.getRegisteredUsers());
    }
    @Test
    public void registerFailUsername() {
        Client register = User.register("client","client1client");
        assertNull(register);
    }

    @Test
    public void registerFailPassword() {
        Client register = User.register("clientclient","client");
        assertNull(register);

    }

    @Test
    public void login() {
        Employee login = (Employee) User.login("employee1", "employee1");
        assertEquals(e1,login);
    }

    @Test
    public void loginFail() {
        Employee login = (Employee) User.login("employee1", "employee2");
        assertNull(login);
    }

    @Test
    public void loginAsGuest() {
        Client guest = User.loginAsGuest();
        assertEquals(new Client("guest","guest"), guest);
    }

    @Test
    public void addToRegisteredUsers() {
        User.addToRegisteredUsers(c2);
        assertEquals(set2, User.getRegisteredUsers());
    }

    @Test
    public void removeFromRegisteredUsers() {
        User.removeFromRegisteredUsers(c2);
        assertEquals(set, User.getRegisteredUsers());
    }
}