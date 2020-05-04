package com.example.pcstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ComponentTest {

    Component pcCase,cpu,gpu;
    Set<ConnectionPort> gpuProvidedPorts = new HashSet<>();
    Set<ConnectionPort> gpuRequiredPorts = new HashSet<>();
    Set<ConnectionPort> pcCaseSet = new HashSet<>();
    Set<ConnectionPort> cpuSet = new HashSet<>();
    Set<ConnectionPort> gpuSet1 = new HashSet<>();
    Set<ConnectionPort> gpuSet2 = new HashSet<>();

    @Before
    public void setUp() throws Exception {

        gpuProvidedPorts.add(new ConnectionPort("hdmi", null));
        gpuProvidedPorts.add(new ConnectionPort("displayport", null));
        gpuRequiredPorts.add(new ConnectionPort("pcie3", null));
        gpuRequiredPorts.add(new ConnectionPort("pcie4", null));

        pcCase = new Component("1","pcCase",10,100,"Component", new ComponentType("case", true), new HashSet<ConnectionPort>(), null);
        pcCaseSet.add(new ConnectionPort("atx", null));
        cpu = new Component("2","cpu",10,200,"Component", new ComponentType("cpu", true), null, new HashSet<ConnectionPort>());
        cpuSet.add(new ConnectionPort("am4", null));
        gpu = new Component("5","gpu",10,200,"Component", new ComponentType("gpu", true), gpuProvidedPorts, gpuRequiredPorts);
        gpuSet1.add(new ConnectionPort("hdmi", null));
        gpuSet2.add(new ConnectionPort("pcie3", null));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToProvidedPorts() {
        pcCase.addToProvidedPorts(new ConnectionPort("atx", null));
        assertEquals(pcCaseSet,pcCase.getProvidedPorts());
    }

    @Test
    public void removeFromProvidedPorts() {
        gpu.removeFromProvidedPorts(new ConnectionPort("displayport", null));
        assertEquals(gpuSet1,gpu.getProvidedPorts());
    }

    @Test
    public void addToRequiredPorts() {
        cpu.addToRequiredPorts(new ConnectionPort("am4", null));
        assertEquals(cpuSet,cpu.getRequiredPorts());
    }

    @Test
    public void removeFromRequiredPorts() {
        gpu.removeFromRequiredPorts(new ConnectionPort("pcie4", null));
        assertEquals(gpuSet2,gpu.getRequiredPorts());
    }
}