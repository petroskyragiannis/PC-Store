package com.example.pcstore.dao;

import com.example.pcstore.model.Address;
import com.example.pcstore.model.CardInfo;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.ComponentType;
import com.example.pcstore.model.ConnectionPort;
import com.example.pcstore.model.Delivery;
import com.example.pcstore.model.Employee;
import com.example.pcstore.model.Hardware;
import com.example.pcstore.model.Order;
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.Payment;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;

public abstract class Initializer {

    protected abstract void eraseData();

    public void prepareData() {
        eraseData();

        ClientDAO clientDAO = getClientDAO();
        clientDAO.save(new Client("petraros","petraros","Petros","Kyragiannis","6940000000","petraros@gmail.com",
                new Address("Thiseos","1","Kifisia","14561"),
                new CardInfo("Petros Kyragiannis", "0000",
                new GregorianCalendar(1,1,2021),"000")));
        clientDAO.save(new Client("boubas","boubasboubas","Petros","Boubalis","6970000000","boubas@gmail.com",
                new Address("Thiseos","2","Kifisia","14561"),
                new CardInfo("Petros Boubalis", "0000",
                new GregorianCalendar(1,1,2021),"000")));
        clientDAO.save(new Client("nickolas","nickolas","Nikos","Xasapis","6980000000","nickolas@gmail.com",
                new Address("Thiseos","3","Kifisia","14561"),
                new CardInfo("Nikos Xasapis", "0000",
                new GregorianCalendar(1,1,2021),"000")));

        EmployeeDAO employeeDAO = getEmployeeDAO();
        employeeDAO.save(new Employee("petrospetras","petrospetras","Petros","Kyragiannis","6940000000","petraros@gmail.com"));
        employeeDAO.save(new Employee("boubalini","boubalini","Petros","Boubalis","6970000000","boubas@gmail.com"));
        employeeDAO.save(new Employee("boomdoom","boomdoom","Nikos","Xasapis","6980000000","nickolas@gmail.com"));


        UserDAO userDAO = getUserDAO();

        for (Client client : clientDAO.findAll())
            userDAO.save(client);

        for (Employee employee : employeeDAO.findAll())
            userDAO.save(employee);

        ProductDAO productDAO = getProductDAO();

        productDAO.save(new Product(productDAO.nextId(),"Huawei MateBook D15",10,600, Hardware.LAPTOP));
        productDAO.save(new Product(productDAO.nextId(),"Apple MacBook Pro 16",10,2500, Hardware.LAPTOP));
        productDAO.save(new Product(productDAO.nextId(),"Powertech DMPC-0010",10,900, Hardware.PC));
        productDAO.save(new Product(productDAO.nextId(),"Dell Vostro 3671 MT",10,850, Hardware.PC));
        productDAO.save(new Product(productDAO.nextId(),"Sony PlayStation 4 Pro 1TB",10,350, Hardware.CONSOLE));
        productDAO.save(new Product(productDAO.nextId(),"Microsoft Xbox One X 1TB",10,350, Hardware.CONSOLE));

        ComponentDAO componentDAO = getComponentDAO();

        componentDAO.save(new Component(productDAO.nextId(), "Cougar MX330-X", 10, 40, Hardware.COMPONENT,
                new ComponentType(Hardware.CASE,true),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.MINI_ITX, ""),
                        new ConnectionPort(Hardware.MICRO_ATX, ""),
                        new ConnectionPort(Hardware.ATX, ""))),
                new HashSet<ConnectionPort>()));

        componentDAO.save(new Component(productDAO.nextId(), "NZXT H510", 10, 100, Hardware.COMPONENT,
                new ComponentType(Hardware.CASE,true),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.MINI_ITX, ""),
                        new ConnectionPort(Hardware.MICRO_ATX, ""),
                        new ConnectionPort(Hardware.ATX, ""))),
                new HashSet<ConnectionPort>()));

        componentDAO.save(new Component(productDAO.nextId(), "AMD Ryzen 5 3600", 10, 200, Hardware.COMPONENT,
                new ComponentType(Hardware.CPU,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.AM4, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Intel Core i9-9900K", 10, 500, Hardware.COMPONENT,
                new ComponentType(Hardware.CPU,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.LGA_1151, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "MSI B450 Tomahawk Max", 10, 100, Hardware.COMPONENT,
                new ComponentType(Hardware.MOTHERBOARD,true),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.AM4, ""),
                        new ConnectionPort(Hardware.DDR4, ""),
                        new ConnectionPort(Hardware.DIMM1, ""),
                        new ConnectionPort(Hardware.DIMM2, ""),
                        new ConnectionPort(Hardware.DIMM4, ""),
                        new ConnectionPort(Hardware.PCIE3, ""),
                        new ConnectionPort(Hardware.PCIE4, ""),
                        new ConnectionPort(Hardware.SATA3, ""),
                        new ConnectionPort(Hardware.M2, ""),
                        new ConnectionPort(Hardware.USB2, ""),
                        new ConnectionPort(Hardware.USB3, ""))),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.ATX, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Asus ROG Strix Z390 Gaming", 10, 200, Hardware.COMPONENT,
                new ComponentType(Hardware.MOTHERBOARD,true),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.LGA_1151, ""),
                        new ConnectionPort(Hardware.DDR4, ""),
                        new ConnectionPort(Hardware.DIMM1, ""),
                        new ConnectionPort(Hardware.DIMM2, ""),
                        new ConnectionPort(Hardware.DIMM4, ""),
                        new ConnectionPort(Hardware.PCIE3, ""),
                        new ConnectionPort(Hardware.SATA3, ""),
                        new ConnectionPort(Hardware.M2, ""),
                        new ConnectionPort(Hardware.USB2, ""),
                        new ConnectionPort(Hardware.USB3, ""))),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.ATX, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "G.Skill TridentZ RGB 16GB DDR4-3200MHZ", 10, 100, Hardware.COMPONENT,
                new ComponentType(Hardware.RAM,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.DDR4, ""),
                        new ConnectionPort(Hardware.DIMM2, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Corsair Vengeance LPX 8GB DDR4-3000MHZ", 10, 50, Hardware.COMPONENT,
                new ComponentType(Hardware.RAM,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.DDR4, ""),
                        new ConnectionPort(Hardware.DIMM1, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Gigabyte GeForce GTX 1660 6GB OC", 10, 250, Hardware.COMPONENT,
                new ComponentType(Hardware.GPU,true),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.HDMI, ""),
                        new ConnectionPort(Hardware.DPORT, ""))),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.PCIE3, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Sapphire Radeon RX 5700 XT 8GB Nitro+", 10, 450, Hardware.COMPONENT,
                new ComponentType(Hardware.GPU,true),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.HDMI, ""),
                        new ConnectionPort(Hardware.DPORT, ""))),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.PCIE4, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Western Digital Caviar Blue 1TB 7200RPM", 10, 50, Hardware.COMPONENT,
                new ComponentType(Hardware.HARD_DRIVE,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.SATA3, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Samsung 970 Evo Plus 500GB", 10, 100, Hardware.COMPONENT,
                new ComponentType(Hardware.HARD_DRIVE,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.M2, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Corsair TX650M", 10, 100, Hardware.COMPONENT,
                new ComponentType(Hardware.PSU,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>()));

        componentDAO.save(new Component(productDAO.nextId(), "Thermaltake Smart RGB 500W", 10, 50, Hardware.COMPONENT,
                new ComponentType(Hardware.PSU,true),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>()));

        componentDAO.save(new Component(productDAO.nextId(), "Razer Deathadder V2", 10, 70, Hardware.COMPONENT,
                new ComponentType(Hardware.MOUSE,false),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.USB3, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Logitech MX Master 3", 10, 100, Hardware.COMPONENT,
                new ComponentType(Hardware.MOUSE,false),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.USB3, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Motospeed CK108", 10, 50, Hardware.COMPONENT,
                new ComponentType(Hardware.KEYBOARD,false),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.USB3, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Logitech K400 Plus", 10, 30, Hardware.COMPONENT,
                new ComponentType(Hardware.KEYBOARD,false),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.USB3, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "AOC 24G2U", 10, 250, Hardware.COMPONENT,
                new ComponentType(Hardware.MONITOR,false),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.HDMI, ""),
                        new ConnectionPort(Hardware.DPORT, "")))));

        componentDAO.save(new Component(productDAO.nextId(), "Dell S2719DGF", 10, 350, Hardware.COMPONENT,
                new ComponentType(Hardware.MONITOR,false),
                new HashSet<ConnectionPort>(),
                new HashSet<ConnectionPort>(Arrays.asList(
                        new ConnectionPort(Hardware.HDMI, ""),
                        new ConnectionPort(Hardware.DPORT, "")))));

        for (Component component: componentDAO.findAll())
            productDAO.save(component);

        SimpleOrderLineDAO simpleOrderLineDAO = getSimpleOrderLineDAO();
        simpleOrderLineDAO.save(new SimpleOrderLine(productDAO.findByName("AOC 24G2U")));
        simpleOrderLineDAO.save(new SimpleOrderLine(productDAO.findByName("Apple MacBook Pro 16")));
        simpleOrderLineDAO.save(new SimpleOrderLine(productDAO.findByName("Western Digital Caviar Blue 1TB 7200RPM")));
        simpleOrderLineDAO.save(new SimpleOrderLine(productDAO.findByName("Razer Deathadder V2")));

        PcConfigurationDAO pcConfigurationDAO =  getPcConfigurationDAO();

        pcConfigurationDAO.save(new PcConfiguration(
                componentDAO.findByName("Cougar MX330-X"),
                componentDAO.findByName("AMD Ryzen 5 3600"),
                componentDAO.findByName("MSI B450 Tomahawk Max"),
                componentDAO.findByName("G.Skill TridentZ RGB 16GB DDR4-3200MHZ"),
                componentDAO.findByName("Sapphire Radeon RX 5700 XT 8GB Nitro+"),
                componentDAO.findByName("Samsung 970 Evo Plus 500GB"),
                componentDAO.findByName("Thermaltake Smart RGB 500W"),
                componentDAO.findByName("Razer Deathadder V2"),
                componentDAO.findByName("Motospeed CK108"),
                componentDAO.findByName("AOC 24G2U")));

        pcConfigurationDAO.save(new PcConfiguration(
                componentDAO.findByName("NZXT H510"),
                componentDAO.findByName("Intel Core i9-9900K"),
                componentDAO.findByName("Asus ROG Strix Z390 Gaming"),
                componentDAO.findByName("Corsair Vengeance LPX 8GB DDR4-3000MHZ"),
                componentDAO.findByName("Gigabyte GeForce GTX 1660 6GB OC"),
                componentDAO.findByName("Western Digital Caviar Blue 1TB 7200RPM"),
                componentDAO.findByName("Corsair TX650M"),
                componentDAO.findByName("Logitech MX Master 3"),
                componentDAO.findByName("Logitech K400 Plus"),
                componentDAO.findByName("Dell S2719DGF")));


        OrderDAO orderDAO = getOrderDAO();

        orderDAO.save(new Order(orderDAO.nextId(),clientDAO.find("petraros"),
                new HashSet<OrderLine>(Arrays.asList(pcConfigurationDAO.find(componentDAO.findByName("AMD Ryzen 5 3600")))), false,
                new GregorianCalendar(),
                Delivery.STORE, Payment.ON_PICKUP));

        orderDAO.save(new Order(orderDAO.nextId(),clientDAO.find("boubas"),
                new HashSet<OrderLine>(Arrays.asList(
                        pcConfigurationDAO.find(componentDAO.findByName("Intel Core i9-9900K")),
                        simpleOrderLineDAO.find(productDAO.findByName("Apple MacBook Pro 16"), 1))),
                false, new GregorianCalendar(), Delivery.ADDRESS, Payment.CARD));

        orderDAO.save(new Order(orderDAO.nextId(),clientDAO.find("boubas"),
                new HashSet<OrderLine>(Arrays.asList(
                        simpleOrderLineDAO.find(productDAO.findByName("Western Digital Caviar Blue 1TB 7200RPM"), 5),
                        simpleOrderLineDAO.find(productDAO.findByName("Razer Deathadder V2"), 1))),
                true, new GregorianCalendar(2020,4,10), Delivery.ADDRESS, Payment.CARD));
    }

    public abstract ClientDAO getClientDAO();
    public abstract ComponentDAO getComponentDAO();
    public abstract EmployeeDAO getEmployeeDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract PcConfigurationDAO getPcConfigurationDAO();
    public abstract ProductDAO getProductDAO();
    public abstract SimpleOrderLineDAO getSimpleOrderLineDAO();
    public abstract UserDAO getUserDAO();
}
