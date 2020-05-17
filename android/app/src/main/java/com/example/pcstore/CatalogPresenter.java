package com.example.pcstore;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.Hardware;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CatalogPresenter {

    private CatalogView view;
    private ProductDAO productDAO;

    public CatalogPresenter() {}

    public List<Product> getCatalog() {
        return productDAO.findAll();
    }

    public List<Product> getComponents(String componentType) {
        List<Product> components = new ArrayList<>();
        List<Product> catalog = getCatalog();
        for (Product product: catalog) {
            if (product instanceof Component) {
                Component component = (Component) product;
                Hardware hardwareType = Hardware.valueOf(componentType);
                if (component.getType().getName().equals(hardwareType))
                    components.add(product);
            }
        }
        return components;
    }

    public void onProductSelected(Client client, Product product) {
        client.addToCart(product, 1);
        view.showStatus(product.getName() + " added to cart.");
    }

    public void onComponentSelected(PcConfiguration configuration, Component component, String componentType) {

        Hardware hardwareType = Hardware.valueOf(componentType);

        if (hardwareType == Hardware.CASE) {
            configuration.setPcCase(component);
            if (configuration.getPcCase()== null)
                view.showStatus("Could not add this case.");
            else
                view.showStatus("Case added successfully.");
        }
        else if (hardwareType == Hardware.CPU) {
            configuration.setCpu(component);
            if (configuration.getCpu()== null)
                view.showStatus("Could not add this cpu.");
            else
                view.showStatus("Cpu added successfully.");
        }
        else if (hardwareType == Hardware.MOTHERBOARD) {
            configuration.setMotherboard(component);
            if (configuration.getMotherboard()== null)
                view.showStatus("Could not add this motherboard.");
            else
                view.showStatus("Motherboard added successfully.");
        }
        else if (hardwareType == Hardware.RAM) {
            configuration.setRam(component);
            if (configuration.getRam()== null)
                view.showStatus("Could not add this ram.");
            else
                view.showStatus("Ram added successfully.");
        }
        else if (hardwareType == Hardware.GPU) {
            configuration.setGpu(component);
            if (configuration.getGpu()== null)
                view.showStatus("Could not add this gpu.");
            else
                view.showStatus("Gpu added successfully.");
        }
        else if (hardwareType == Hardware.HARD_DRIVE) {
            configuration.setHardDrive(component);
            if (configuration.getHardDrive()== null)
                view.showStatus("Could not add this hard drive.");
            else
                view.showStatus("Hard drive added successfully.");
        }
        else if (hardwareType == Hardware.PSU) {
            configuration.setPsu(component);
            if (configuration.getPsu()== null)
                view.showStatus("Could not add this psu.");
            else
                view.showStatus("Psu added successfully.");
        }
        else if (hardwareType == Hardware.MOUSE) {
            configuration.setMouse(component);
            if (configuration.getMouse()== null)
                view.showStatus("Could not add this mouse.");
            else
                view.showStatus("Mouse added successfully.");
        }
        else if (hardwareType == Hardware.KEYBOARD) {
            configuration.setKeyboard(component);
            if (configuration.getKeyboard()== null)
                view.showStatus("Could not add this keyboard.");
            else
                view.showStatus("Keyboard added successfully.");
        }
        else if (hardwareType == Hardware.MONITOR) {
            configuration.setMonitor(component);
            if (configuration.getMonitor()== null)
                view.showStatus("Could not add this monitor.");
            else
                view.showStatus("Monitor added successfully.");
        }
        else view.showStatus("Wrong component type.");
    }

    public void setView(CatalogView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
