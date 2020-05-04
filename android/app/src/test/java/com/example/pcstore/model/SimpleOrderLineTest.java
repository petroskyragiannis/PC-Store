package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.ConnectException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SimpleOrderLineTest {

    SimpleOrderLine o1,o2;
    Product p;
    Component c;
    Set<ConnectionPort> cpuRequiredPorts = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        p = new Product("1","pc",10,1500,"PC");
        cpuRequiredPorts.add(new ConnectionPort("am4", null));
        c = new Component("2","cpu",10,200,"Component", new ComponentType("cpu", true), null, cpuRequiredPorts);
        o1 = new SimpleOrderLine(p,5);
        o2 = new SimpleOrderLine(c,15);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSubTotal() {
        assertEquals(7500,o1.getSubTotal());

    }

    @Test
    public void updateStock() {
        o1.updateStock();
        assertEquals(5,p.getStock());
    }

    @Test
    public void updateStockFail() {
        o2.updateStock();
        assertEquals(10,c.getStock());
    }
}