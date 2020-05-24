package com.example.pcstore;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.Hardware;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ComponentPresenter {

    private ComponentView view;
    private ProductDAO productDAO;

    public ComponentPresenter() {}

    public List<Component> getComponents(String componentType) {
        List<Component> components = new ArrayList<>();
        List<Product> catalog = productDAO.findAll();
        for (Product product: catalog) {
            if (product instanceof Component) {
                Component component = (Component) product;
                Hardware hardwareType = Hardware.valueOf(componentType);
                if (component.getType().getName().equals(hardwareType))
                    components.add(component);
            }
        }
        return components;
    }

    public void onComponentSelected(PcConfiguration configuration, Component component, String componentType) {
        boolean flag = false;
        Hardware hardwareType = Hardware.valueOf(componentType);
        if (hardwareType == Hardware.CASE) {
            configuration.setPcCase(component);
            if (configuration.getPcCase() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.CPU) {
            configuration.setCpu(component);
            if (configuration.getCpu() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.MOTHERBOARD) {
            configuration.setMotherboard(component);
            if (configuration.getMotherboard() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.RAM) {
            configuration.setRam(component);
            if (configuration.getRam() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.GPU) {
            configuration.setGpu(component);
            if (configuration.getGpu()!= null)
                flag=true;
        }
        else if (hardwareType == Hardware.HARD_DRIVE) {
            configuration.setHardDrive(component);
            if (configuration.getHardDrive() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.PSU) {
            configuration.setPsu(component);
            if (configuration.getPsu() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.MOUSE) {
            configuration.setMouse(component);
            if (configuration.getMouse() != null)
                flag=true;
        }
        else if (hardwareType == Hardware.KEYBOARD) {
            configuration.setKeyboard(component);
            if (configuration.getKeyboard()!= null)
                flag=true;
        }
        else if (hardwareType == Hardware.MONITOR) {
            configuration.setMonitor(component);
            if (configuration.getMonitor()!= null)
                flag=true;
        }
        if (!flag) view.showStatus("Could not add " + component.getName() + ".");
    }


    public void returnPcConfiguration(PcConfiguration pcConfiguration, Component component) {
        view.returnPcConfiguration(pcConfiguration, component);
    }

    public void setView(ComponentView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

}
