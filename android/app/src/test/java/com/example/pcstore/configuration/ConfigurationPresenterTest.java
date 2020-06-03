package com.example.pcstore.configuration;

import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.PcConfigurationDAOMemory;
import com.example.pcstore.memorydao.ProductDAOMemory;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigurationPresenterTest {
    ConfigurationPresenter presenter;
    ConfigurationViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new ConfigurationViewStub();
        presenter = new ConfigurationPresenter();
        presenter.setView(view);
    }

    @Test
    public void successfulCheckPcConfiguration() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        boolean flag = presenter.checkPcConfiguration(pc);
        assertTrue(flag);
        assertNull(view.getStatus());
    }

    @Test
    public void failCheckPcConfiguration() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Component nzxt = (Component) productDAO.findByName("NZXT H510");
        Component amd = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(nzxt);
        pc.setCpu(amd);
        boolean flag = presenter.checkPcConfiguration(pc);
        assertFalse(flag);
        assertEquals("CPU and MOTHERBOARD are not compatible.", view.getStatus());

    }

    @Test
    public void returnPcConfiguration() {
        ProductDAOMemory productDAO = new ProductDAOMemory();
        PcConfigurationDAOMemory pcConfigurationDAO = new PcConfigurationDAOMemory();
        Component component = (Component) productDAO.findByName("AMD Ryzen 5 3600");
        PcConfiguration pc = pcConfigurationDAO.find(component);
        presenter.returnPcConfiguration(pc);
        assertEquals(pc, view.getPcConfiguration());
    }

}