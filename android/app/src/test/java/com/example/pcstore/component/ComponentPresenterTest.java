package com.example.pcstore.component;

import com.example.pcstore.configuration.ConfigurationPresenter;
import com.example.pcstore.configuration.ConfigurationViewStub;
import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.PcConfigurationDAOMemory;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.Hardware;
import com.example.pcstore.model.PcConfiguration;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ComponentPresenterTest {
    ComponentPresenter presenter;
    ComponentViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new ComponentViewStub();
        presenter = new ComponentPresenter();
        presenter.setView(view);
        presenter.setProductDAO(new ProductDAOMemory());
    }

    @Test
    public void getCPUs() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        Component cpu1 = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        Component cpu2 = (Component) productDAO.findByName("Intel Core i9-9900K");
        List<Component> cpuList = new ArrayList<>();
        cpuList.add(cpu1);
        cpuList.add(cpu2);
        List<Component> result = presenter.getComponents("CPU");
        assertEquals(cpuList, result);
    }

    @Test
    public void selectCase() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Component cpu = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        Component pcCase = (Component) productDAO.findByName("NZXT H510");
        PcConfiguration pc = pcConfigurationDAO.find(cpu);
        presenter.onComponentSelected(pc, pcCase, "CASE");
        assertEquals(pc.getPcCase(), pcCase);
        assertNull(view.getStatus());
    }

    // Same for CPU, Motherboard, Ram, GPU, Hard Drive, PSU, Mouse, Keyboard

    @Test
    public void selectMonitor() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Component cpu = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        Component monitor = (Component) productDAO.findByName("Dell S2719DGF");
        PcConfiguration pc = pcConfigurationDAO.find(cpu);
        presenter.onComponentSelected(pc, monitor, "MONITOR");
        assertEquals(pc.getMonitor(), monitor);
        assertNull(view.getStatus());
    }

    @Test
    public void returnPcConfiguration() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        presenter.returnPcConfiguration(pc, component);
        assertEquals(pc, view.getPcConfiguration());
        assertEquals(component, view.getComponent());
    }

}