package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class OrderTest {

    Client client;
    Set<OrderLine> set = new HashSet<>();
    Set<OrderLine> set2 = new HashSet<>();
    Order order,order2,order3,order4;
    Product p;
    PcConfiguration config;
    Component pcCase,cpu,motherboard,ram,gpu,hardDrive,psu,mouse,keyboard,monitor,cpu2;
    Set<ConnectionPort> pcCaseProvidedPorts = new HashSet<>();
    Set<ConnectionPort> cpuRequiredPorts = new HashSet<>();
    Set<ConnectionPort> cpuRequiredPorts2 = new HashSet<>();
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

        client = new Client("client", "clientclient");
        p = new Product("11","pc",10,1500,"PC");
        cpuRequiredPorts2.add(new ConnectionPort("lga1150", null));
        cpu2 = new Component("12","cpu2",10,200,"Component", new ComponentType("cpu", true), null, cpuRequiredPorts2);

        set.add(new SimpleOrderLine(p,10));
        set.add(new SimpleOrderLine(cpu2,5));
        order = new Order(client,new HashSet<OrderLine>());

        set2.add(new SimpleOrderLine(cpu2,5));
        order2 = new Order(client,set);
        order.setOrderDate(new GregorianCalendar(2020,10,5));
        order2.setOrderDate(new GregorianCalendar(2020,10,5));

        config = new PcConfiguration();
        setUpConfig(config);
        order3 = new Order(client,new HashSet<OrderLine>());
        order3.addOrderLine(new SimpleOrderLine(p,10));
        order3.addOrderLine(new SimpleOrderLine(cpu2,5));
        order3.addOrderLine(config);

        order4 = new Order(client,new HashSet<OrderLine>());
        order4.addOrderLine(config);


    }

    @After
    public void tearDown() throws Exception {
        Order.setOrders(new HashSet<Order>());
    }

    @Test
    public void getMonthlyReport() {
        int month = order3.getOrderDate().get(Calendar.MONTH);
        assertEquals(18400, Order.getMonthlyReport(month));
    }

    @Test
    public void addOrderLine() {
        order.addOrderLine(new SimpleOrderLine(p,10));
        order.addOrderLine(new SimpleOrderLine(cpu2,5));
        assertEquals(set,order.getOrderLines());
    }

    @Test
    public void removeOrderLine() {
        order2.removeOrderLine(new SimpleOrderLine(p,10));
        assertEquals(set2,order2.getOrderLines());
    }

    @Test
    public void getTotal() {
        assertEquals(17200,order3.getTotal());
    }

    @Test
    public void updateStock() {
        order3.updateStock();
        assertEquals(0,p.getStock());
        assertEquals(5,cpu2.getStock());
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