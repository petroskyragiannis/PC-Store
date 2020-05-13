package com.example.pcstore;

import com.example.pcstore.dao.ClientDAO;
import com.example.pcstore.dao.ComponentDAO;
import com.example.pcstore.dao.EmployeeDAO;
import com.example.pcstore.dao.Initializer;
import com.example.pcstore.dao.OrderDAO;
import com.example.pcstore.dao.PcConfigurationDAO;
import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.dao.SimpleOrderLineDAO;
import com.example.pcstore.dao.UserDAO;
import com.example.pcstore.memorydao.ClientDAOMemory;
import com.example.pcstore.memorydao.ComponentDAOMemory;
import com.example.pcstore.memorydao.EmployeeDAOMemory;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.OrderDAOMemory;
import com.example.pcstore.memorydao.PcConfigurationDAOMemory;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.memorydao.SimpleOrderLineDAOMemory;
import com.example.pcstore.memorydao.UserDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DAOTest {

    private UserDAO userDAO;
    private ClientDAO clientDAO;
    private EmployeeDAO employeeDAO;
    private ProductDAO productDAO;
    private ComponentDAO componentDAO;
    private SimpleOrderLineDAO simpleOrderLineDAO;
    private PcConfigurationDAO pcConfigurationDAO;
    private OrderDAO orderDAO;

    private static final int USER_COUNT = 6;
    private static final int CLIENT_COUNT = 3;
    private static final int EMPLOYEE_COUNT = 3;
    private static final int PRODUCT_COUNT = 26;
    private static final int COMPONENT_COUNT = 20;
    private static final int SIMPLEORDERLINE_COUNT = 4;
    private static final int PCCONFIGURATION_COUNT = 2;
    private static final int ORDER_COUNT = 3;

    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        userDAO = new UserDAOMemory();
        clientDAO = new ClientDAOMemory();
        employeeDAO = new EmployeeDAOMemory();
        productDAO = new ProductDAOMemory();
        componentDAO = new ComponentDAOMemory();
        simpleOrderLineDAO = new SimpleOrderLineDAOMemory();
        pcConfigurationDAO = new PcConfigurationDAOMemory();
        orderDAO = new OrderDAOMemory();
    }

    @Test
    public void sizeCheck() {
        Assert.assertEquals(USER_COUNT, userDAO.findAll().size());
        Assert.assertEquals(CLIENT_COUNT, clientDAO.findAll().size());
        Assert.assertEquals(EMPLOYEE_COUNT, employeeDAO.findAll().size());
        Assert.assertEquals(PRODUCT_COUNT, productDAO.findAll().size());
        Assert.assertEquals(COMPONENT_COUNT, componentDAO.findAll().size());
        Assert.assertEquals(SIMPLEORDERLINE_COUNT, simpleOrderLineDAO.findAll().size());
        Assert.assertEquals(PCCONFIGURATION_COUNT, pcConfigurationDAO.findAll().size());
        Assert.assertEquals(ORDER_COUNT, orderDAO.findAll().size());
    }

}
