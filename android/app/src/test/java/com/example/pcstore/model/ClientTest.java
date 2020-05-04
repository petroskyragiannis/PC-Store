package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ClientTest {

    Client client,client1,client2,client3,client4;
    Order order;
    Product p1,p2;
    PcConfiguration config;
    Component pcCase,cpu,motherboard,ram,gpu,hardDrive,psu,mouse,keyboard,monitor;

    Set<Product> wishlistSet1 = new HashSet<>();
    Set<Product> wishlistSet2 = new HashSet<>();
    Set<OrderLine> cartSet1 = new HashSet<>();
    Set<OrderLine> cartSet2 = new HashSet<>();
    Set<OrderLine> cartSet3 = new HashSet<>();
    Set<OrderLine> cartSet4 = new HashSet<>();

    Set<ConnectionPort> pcCaseProvidedPorts = new HashSet<>();
    Set<ConnectionPort> cpuRequiredPorts = new HashSet<>();
    Set<ConnectionPort> motherboardRequiredPorts = new HashSet<>();
    Set<ConnectionPort> motherboardProvidedPorts = new HashSet<>();
    Set<ConnectionPort> ramRequiredPorts = new HashSet<>();
    Set<ConnectionPort> gpuProvidedPorts = new HashSet<>();
    Set<ConnectionPort> gpuRequiredPorts = new HashSet<>();
    Set<ConnectionPort> hardDriveRequiredPorts = new HashSet<>();
    Set<ConnectionPort> mouseRequiredPorts = new HashSet<>();
    Set<ConnectionPort> keyboardRequiredPorts = new HashSet<>();
    Set<ConnectionPort> monitorRequiredPorts = new HashSet<>();


    @Before
    public void setUp() throws Exception {
        p1 = new Product("1","pc1",10,1500,"PC");
        p2 = new Product("2", "pc2",10,1000,"PC");


        config = new PcConfiguration();
        setUpConfig(config);

        client1 = new Client("client1", "client1client1");
        wishlistSet1.add(p1);
        wishlistSet1.add(p2);
        cartSet1.add(new SimpleOrderLine(p1,5));

        client2 = new Client("client2", "client2client2");
        client2.addToWishlist(p1);
        client2.addToWishlist(p2);
        wishlistSet2.add(p1);
        cartSet2.add(config);

        client3 = new Client("client3", "client3client3");
        client3.addToCart(p1,5);
        client3.addToCart(p2,5);
        cartSet3.add(new SimpleOrderLine(p1,5));

        client4 = new Client("client4", "client4client4");
        client4.addToCart(p1,5);
        client4.addToCart(p2,5);
        client4.addToCart(config);
        cartSet4.add(new SimpleOrderLine(p1,5));
        cartSet4.add(new SimpleOrderLine(p2,5));

        client = new Client("client","clientclient");
        client.addToCart(p1,5);
        client.addToCart(p2,5);
        client.addToCart(config);
        order = new Order(client,client.getCart());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToWishlist() {
        client1.addToWishlist(p1);
        client1.addToWishlist(p2);
        assertEquals(wishlistSet1,client1.getWishlist());
    }

    @Test
    public void removeFromWishlist() {
        client2.removeFromWishlist(p2);
        assertEquals(wishlistSet2,client2.getWishlist());
    }

    @Test
    public void addSimpleToCart() {
        client1.addToCart(p1,5);
        assertEquals(cartSet1,client1.getCart());

    }

    @Test
    public void addConfigToCart() {
        client2.addToCart(config);
        assertEquals(cartSet2,client2.getCart());
    }

    @Test
    public void removeSimpleFromCart() {
        client3.removeFromCart(p2,5);
        assertEquals(cartSet3,client3.getCart());
    }

    @Test
    public void removeConfigFromCart() {
        client4.removeFromCart(config);
        assertEquals(cartSet4,client4.getCart());
    }

    //TODO
    @Test
    public void createOrder() {
        Order o = client.createOrder();
        assertEquals(order,o);
        assertEquals(5,p1.getStock());
        assertEquals(5,p2.getStock());

    }

    private void setUpConfig(PcConfiguration config){

        pcCaseProvidedPorts.add(new ConnectionPort("mini itx", null));
        pcCaseProvidedPorts.add(new ConnectionPort("micro atx", null));
        pcCaseProvidedPorts.add(new ConnectionPort("atx", null));
        pcCase = new Component("1","pcCase",10,100,"Component", new ComponentType("case", true), pcCaseProvidedPorts, null);

        cpuRequiredPorts.add(new ConnectionPort("am4", null));
        cpu = new Component("2","cpu",10,200,"Component", new ComponentType("cpu", true), null, cpuRequiredPorts);

        motherboardProvidedPorts.add(new ConnectionPort("am4", null));
        motherboardProvidedPorts.add(new ConnectionPort("ddr4", null));
        motherboardProvidedPorts.add(new ConnectionPort("1 dimm", null));
        motherboardProvidedPorts.add(new ConnectionPort("2 dimms", null));
        motherboardProvidedPorts.add(new ConnectionPort("4 dimms", null));
        motherboardProvidedPorts.add(new ConnectionPort("pcie", null));
        motherboardProvidedPorts.add(new ConnectionPort("sata", null));
        motherboardProvidedPorts.add(new ConnectionPort("m.2", null));
        motherboardProvidedPorts.add(new ConnectionPort("usb", null));
        motherboardRequiredPorts.add(new ConnectionPort("atx", null));
        motherboard = new Component("3","motherboard",10,100,"Component",new ComponentType("motherboard",true),motherboardProvidedPorts,motherboardRequiredPorts);

        ramRequiredPorts.add(new ConnectionPort("2 dimms", null));
        ramRequiredPorts.add(new ConnectionPort("ddr4", null));
        ram = new Component("4","ram",10,100,"Component", new ComponentType("ram", true), null, ramRequiredPorts);

        gpuProvidedPorts.add(new ConnectionPort("hdmi", null));
        gpuProvidedPorts.add(new ConnectionPort("displayport", null));
        gpuRequiredPorts.add(new ConnectionPort("pcie", null));
        gpu = new Component("5","gpu",10,200,"Component", new ComponentType("gpu", true), gpuProvidedPorts, gpuRequiredPorts);

        hardDriveRequiredPorts.add(new ConnectionPort("sata", null));
        hardDrive = new Component("6","hardDrive",10,50,"Component", new ComponentType("hardDrive", true), null, hardDriveRequiredPorts);

        psu = new Component("7","psu",10,100,"Component", new ComponentType("psu", true), null, null);

        mouseRequiredPorts.add(new ConnectionPort("usb", null));
        mouse = new Component("8","mouse",10,50,"Component", new ComponentType("mouse", false), null, mouseRequiredPorts);

        keyboardRequiredPorts.add(new ConnectionPort("usb", null));
        keyboard = new Component("9","keyboard",10,100,"Component", new ComponentType("keyboard", false), null, keyboardRequiredPorts);

        monitorRequiredPorts.add(new ConnectionPort("dvid", null));
        monitorRequiredPorts.add(new ConnectionPort("hdmi", null));
        monitorRequiredPorts.add(new ConnectionPort("displayport", null));
        monitor = new Component("10","monitor",10,200,"Component", new ComponentType("monitor", false), null, monitorRequiredPorts);

        config.setPcCase(pcCase);
        config.setCpu(cpu);
        config.setMotherboard(motherboard);
        config.setRam(ram);
        config.setGpu(gpu);
        config.setHardDrive(hardDrive);
        config.setPsu(psu);
        config.setMouse(mouse);
        config.setKeyboard(keyboard);
        config.setMonitor(monitor);
    }
}