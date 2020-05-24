package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class PcConfiguration extends OrderLine {

    /*
    pcCase: providedPorts
    cpu: requiredPorts
    motherboard: providedPorts, requiredPorts
    ram: requiredPorts
    gpu: providedPorts, requiredPorts
    hardDrive: requiredPorts
    psu: -
    mouse: requiredPorts
    keyboard: requiredPorts
    monitor: requiredPorts
     */
    private Component pcCase;
    private Component cpu;
    private Component motherboard;
    private Component ram;
    private Component gpu;
    private Component hardDrive;
    private Component psu;
    private Component mouse;
    private Component keyboard;
    private Component monitor;

    private Component[] components;

    // Constructor
    public PcConfiguration() {
        super(1);
    }

    public PcConfiguration(Component pcCase, Component cpu, Component motherboard, Component ram, Component gpu, Component hardDrive, Component psu, Component mouse, Component keyboard, Component monitor) {
        super(1);
        this.pcCase = pcCase;
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.ram = ram;
        this.gpu = gpu;
        this.hardDrive = hardDrive;
        this.psu = psu;
        this.mouse = mouse;
        this.keyboard = keyboard;
        this.monitor = monitor;
        components = new Component[] {pcCase, cpu, motherboard, ram, gpu, hardDrive, psu, mouse, keyboard, monitor};
    }

    public boolean checkRequirements() throws CompatibilityException {
        Component[] requiredComponents = {pcCase, cpu, motherboard, ram, gpu, hardDrive, psu};
        for (Component c: requiredComponents) {
            if (c==null) {
                throw new CompatibilityException("Missing Required Component.");
            }
        }
        return  true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean checkCompatibility() throws CompatibilityException {

        boolean flag = false;
        Component[] MotherboardComponents = {cpu,ram,gpu,hardDrive,mouse,keyboard};

        for (Component c: MotherboardComponents) {
            if (c != null) {
                for (ConnectionPort r: c.getRequiredPorts()) {
                    for (ConnectionPort p: motherboard.getProvidedPorts()) {
                        if (p.equals(r)) flag=true;
                    }
                    if (!flag) throw new CompatibilityException(c.getType().getName()+" and MOTHERBOARD are not compatible.");
                }
            }
            if (!flag) break;
        }

        if (!checkCaseCompatibility())
            throw new CompatibilityException("CASE and MOTHERBOARD are not compatible.");
        if (monitor!=null)
            if (!checkMonitorCompatibility())
                throw new CompatibilityException("GPU and MONITOR are not compatible.");

        return flag;
    }

    // PC Case only has provided ports.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkCaseCompatibility() {
        boolean flag = false;
        for (ConnectionPort r: motherboard.getRequiredPorts()) {
            for (ConnectionPort p: pcCase.getProvidedPorts()) {
                if (p.equals(r)) flag=true;
            }
            if (!flag) break;
        }
        return flag;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkMonitorCompatibility() {
        for (ConnectionPort r: monitor.getRequiredPorts()) {
            for (ConnectionPort p: gpu.getProvidedPorts()) {
                if (p.equals(r)) return true;
            }
        }
        return false;
    }

    @Override
    public int getSubTotal() {
        int total = 0;
        for (Component c : components) {
            if (c != null) total += c.getPrice();
        }
        return total;
    }

    @Override
    public void updateStock() {
        for (Component c: components)
            if (c!=null) c.setStock(c.getStock() - quantity);
    }

    @Override
    public int getStock() {
        int minStock = pcCase.getStock();
        for (Component component: components) {
            if (component.getStock() < minStock)
                minStock = component.getStock();
        }
        return minStock;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PcConfiguration)) return false;

        PcConfiguration that = (PcConfiguration) o;

        if (!Objects.equals(pcCase, that.pcCase)) return false;
        if (!Objects.equals(cpu, that.cpu)) return false;
        if (!Objects.equals(motherboard, that.motherboard)) return false;
        if (!Objects.equals(ram, that.ram)) return false;
        if (!Objects.equals(gpu, that.gpu)) return false;
        if (!Objects.equals(hardDrive, that.hardDrive)) return false;
        if (!Objects.equals(psu, that.psu)) return false;
        if (!Objects.equals(mouse, that.mouse)) return false;
        if (!Objects.equals(keyboard, that.keyboard)) return false;
        return Objects.equals(monitor, that.monitor);
    }

    @Override
    public int hashCode() {
        int result = pcCase != null ? pcCase.hashCode() : 0;
        result = 31 * result + (cpu != null ? cpu.hashCode() : 0);
        result = 31 * result + (motherboard != null ? motherboard.hashCode() : 0);
        result = 31 * result + (ram != null ? ram.hashCode() : 0);
        result = 31 * result + (gpu != null ? gpu.hashCode() : 0);
        result = 31 * result + (hardDrive != null ? hardDrive.hashCode() : 0);
        result = 31 * result + (psu != null ? psu.hashCode() : 0);
        result = 31 * result + (mouse != null ? mouse.hashCode() : 0);
        result = 31 * result + (keyboard != null ? keyboard.hashCode() : 0);
        result = 31 * result + (monitor != null ? monitor.hashCode() : 0);
        return result;
    }

    // Getters and Setters
    public Component getPcCase() {
        return pcCase;
    }

    public void setPcCase(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.CASE, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.pcCase = c1;
        }
    }

    public Component getCpu() {
        return cpu;
    }

    public void setCpu(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.CPU, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.cpu = c1;
        }
    }

    public Component getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.MOTHERBOARD, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.motherboard = c1;
        }
    }

    public Component getRam() {
        return ram;
    }

    public void setRam(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.RAM, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.ram = c1;
        }
    }

    public Component getGpu() {
        return gpu;
    }

    public void setGpu(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.GPU, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.gpu = c1;
        }
    }

    public Component getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.HARD_DRIVE, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.hardDrive = c1;
        }
    }

    public Component getPsu() {
        return psu;
    }

    public void setPsu(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.PSU, true);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.psu = c1;
        }
    }

    public Component getMouse() {
        return mouse;
    }

    public void setMouse(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.MOUSE, false);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.mouse = c1;
        }
    }

    public Component getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.KEYBOARD, false);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.keyboard = c1;
        }
    }

    public Component getMonitor() {
        return monitor;
    }

    public void setMonitor(Component c1) {
        ComponentType c2 = new ComponentType(Hardware.MONITOR, false);
        if (c1.getType().equals(c2) && c1.getStock()>0) {
            this.monitor = c1;
        }
    }

    public Component[] getComponents() {
        return components;
    }

    public void setComponents(Component[] components) {
        this.components = components;
    }

    public static class CompatibilityException extends Exception {
        public CompatibilityException(String message) {
            super(message);
        }
    }
}