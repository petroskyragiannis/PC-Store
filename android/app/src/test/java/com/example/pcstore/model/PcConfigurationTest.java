package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PcConfigurationTest {

    PcConfiguration config,config2, config3, config4, config5, config6;
    Component pcCase,cpu,motherboard,ram,gpu,hardDrive,psu,mouse,keyboard,monitor,pcCase2,monitor2,cpu2;
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
    Set<ConnectionPort> pcCaseProvidedPorts2 = new HashSet<>();
    Set<ConnectionPort> monitorRequiredPorts2 = new HashSet<>();
    Set<ConnectionPort> cpuRequiredPorts2 = new HashSet<>();


    @Before
    public void setUp() throws Exception {

        config = new PcConfiguration();

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

        config2 = new PcConfiguration();
        config2.setPcCase(pcCase);
        config2.setCpu(cpu);
        config2.setMotherboard(motherboard);
        config2.setRam(ram);
        config2.setGpu(gpu);
        config2.setHardDrive(hardDrive);
        config2.setPsu(psu);
        config2.setMouse(mouse);
        config2.setKeyboard(keyboard);
        config2.setMonitor(monitor);

        config3 = new PcConfiguration();
        config3.setPcCase(pcCase);
        //config3.setCpu(cpu);
        config3.setMotherboard(motherboard);
        //config3.setRam(ram);
        config3.setGpu(gpu);
        config3.setHardDrive(hardDrive);
        config3.setPsu(psu);

        config4 = new PcConfiguration();
        pcCaseProvidedPorts2.add(new ConnectionPort("e-atx", null));
        pcCase2 = new Component("11","pcCase2",10,100,"Component", new ComponentType("case", true), pcCaseProvidedPorts2, null);
        config4.setPcCase(pcCase2);
        config4.setCpu(cpu);
        config4.setMotherboard(motherboard);
        config4.setRam(ram);
        config4.setGpu(gpu);
        config4.setHardDrive(hardDrive);
        config4.setPsu(psu);

        config5 = new PcConfiguration();
        config5.setPcCase(pcCase);
        config5.setCpu(cpu);
        config5.setMotherboard(motherboard);
        config5.setRam(ram);
        config5.setGpu(gpu);
        config5.setHardDrive(hardDrive);
        config5.setPsu(psu);
        monitorRequiredPorts2.add(new ConnectionPort("vga", null));
        monitor2 = new Component("12","monitor2",10,200,"Component", new ComponentType("monitor", false), null, monitorRequiredPorts2);
        config5.setMonitor(monitor2);

        config6 = new PcConfiguration();
        config6.setPcCase(pcCase);
        cpuRequiredPorts2.add(new ConnectionPort("lga1150", null));
        cpu2 = new Component("13","cpu2",10,200,"Component", new ComponentType("cpu", true), null, cpuRequiredPorts2);
        config6.setCpu(cpu2);
        config6.setMotherboard(motherboard);
        config6.setRam(ram);
        config6.setGpu(gpu);
        config6.setHardDrive(hardDrive);
        config6.setPsu(psu);
    }



    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setPcCase() {
        config.setPcCase(pcCase);
        assertEquals(config.getPcCase(), new Component("1","pcCase",10,100,"Component", new ComponentType("case", true), pcCaseProvidedPorts, null));
    }
    @Test
    public void setCpu() {
        config.setCpu(cpu);
        assertEquals(config.getCpu(), new Component("2","cpu",10,200,"Component", new ComponentType("cpu", true), null, cpuRequiredPorts));
    }

    @Test
    public void setMotherboard() {
        config.setMotherboard(motherboard);
        assertEquals(config.getMotherboard(), new Component("3","motherboard",10,100,"Component",new ComponentType("motherboard",true),motherboardProvidedPorts,motherboardRequiredPorts));
    }

    @Test
    public void setRam() {
        config.setRam(ram);
        assertEquals(config.getRam(), new Component("4","ram",10,100,"Component", new ComponentType("ram", true), null, ramRequiredPorts));
    }

    @Test
    public void setGpu() {
        config.setGpu(gpu);
        assertEquals(config.getGpu(), new Component("5","gpu",10,200,"Component", new ComponentType("gpu", true), gpuProvidedPorts, gpuRequiredPorts));
    }

    @Test
    public void setHardDrive() {
        config.setHardDrive(hardDrive);
        assertEquals(config.getHardDrive(), new Component("6","hardDrive",10,50,"Component", new ComponentType("hardDrive", true), null, hardDriveRequiredPorts));
    }

    @Test
    public void setPsu() {
        config.setPsu(psu);
        assertEquals(config.getPsu(), new Component("7","psu",10,100,"Component", new ComponentType("psu", true), null, null));
    }

    @Test
    public void setMouse() {
        config.setMouse(mouse);
        assertEquals(config.getMouse(), new Component("8","mouse",10,50,"Component", new ComponentType("mouse", false), null, mouseRequiredPorts));
    }

    @Test
    public void setKeyboard() {
        config.setKeyboard(keyboard);
        assertEquals(config.getKeyboard(), new Component("9","keyboard",10,100,"Component", new ComponentType("keyboard", false), null, keyboardRequiredPorts));
    }

    @Test
    public void setMonitor() {
        config.setMonitor(monitor);
        assertEquals(config.getMonitor(), new Component("10","monitor",10,200,"Component", new ComponentType("monitor", false), null, monitorRequiredPorts));
    }

    @Test
    public void updateStock() {
        config2.updateStock();
        assertEquals(9, cpu.getStock());
    }

    @Test
    public void getSubTotal() {
        assertEquals(1200, config2.getSubTotal());

    }

    @Test
    public void checkRequirements() {
        assertTrue(config2.checkRequirements());
    }

    @Test
    public void checkRequirementsFail() {
        assertFalse(config3.checkRequirements());

    }

    @Test
    public void checkCompatibility() {
        assertTrue(config2.checkCompatibility());
    }

    @Test
    // Case-Motherboard compatibility issue.
    public void checkCompatibilityIssue1() {
        assertFalse(config4.checkCompatibility());
    }

    @Test
    // Monitor-GPU compatibility issue.
    public void checkCompatibilityIssue2() {
        assertFalse(config5.checkCompatibility());
    }

    @Test
    // Other Motherboard compatibility issue.
    public void checkCompatibilityIssue3() {
        assertFalse(config6.checkCompatibility());
    }

}