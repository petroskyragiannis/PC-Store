package com.example.pcstore;

import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.Hardware;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductPresenter {

    private CatalogView view;
    private ProductDAO productDAO;

    public ProductPresenter() {}

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

    public boolean onComponentSelected(PcConfiguration configuration, Component component, String componentType) {
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
        if (flag) view.showStatus(component.getName() + " added successfully.");
        else view.showStatus("Could not add " + component.getName() + ".");
        return flag;
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
