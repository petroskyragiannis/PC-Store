package com.example.pcstore.memorydao;

import com.example.pcstore.dao.AddressDAO;
import com.example.pcstore.dao.CardInfoDAO;
import com.example.pcstore.dao.ClientDAO;
import com.example.pcstore.dao.ComponentDAO;
import com.example.pcstore.dao.ComponentTypeDAO;
import com.example.pcstore.dao.ConnectionPortDAO;
import com.example.pcstore.dao.EmployeeDAO;
import com.example.pcstore.dao.Initializer;
import com.example.pcstore.dao.OrderDAO;
import com.example.pcstore.dao.PcConfigurationDAO;
import com.example.pcstore.dao.ProductDAO;
import com.example.pcstore.dao.SimpleOrderLineDAO;
import com.example.pcstore.dao.UserDAO;
import com.example.pcstore.model.Address;
import com.example.pcstore.model.CardInfo;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.ComponentType;
import com.example.pcstore.model.ConnectionPort;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import com.example.pcstore.model.SimpleOrderLine;
import com.example.pcstore.model.User;

public class MemoryInitializer extends Initializer {

    @Override
    protected void eraseData() {
        
        for (Address address: getAddressDAO().findAll())
            getAddressDAO().delete(address);

        for (CardInfo cardInfo: getCardInfoDAO().findAll())
            getCardInfoDAO().delete(cardInfo);

        for (Client client: getClientDAO().findAll())
            getClientDAO().delete(client);

        for (Component component: getComponentDAO().findAll())
            getComponentDAO().delete(component);

        for (ComponentType componentType: getComponentTypeDAO().findAll())
            getComponentTypeDAO().delete(componentType);

        for (ConnectionPort connectionPort: getConnectionPortDAO().findAll())
            getConnectionPortDAO().delete(connectionPort);

        for (PcConfiguration pcConfiguration: getPcConfigurationDAO().findAll())
            getPcConfigurationDAO().delete(pcConfiguration);

        for (Product product: getProductDAO().findAll())
            getProductDAO().delete(product);

        for (SimpleOrderLine simpleOrderLine: getSimpleOrderLineDAO().findAll())
            getSimpleOrderLineDAO().delete(simpleOrderLine);

        for (User user: getUserDAO().findAll())
            getUserDAO().delete(user);

    }

    @Override
    public AddressDAO getAddressDAO() {
        return new AddressDAOMemory();
    }

    @Override
    public CardInfoDAO getCardInfoDAO() {
        return new CardInfoDAOMemory();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new ClientDAOMemory();
    }

    @Override
    public ComponentDAO getComponentDAO() {
        return new ComponentDAOMemory();
    }

    @Override
    public ComponentTypeDAO getComponentTypeDAO() {
        return new ComponentTypeDAOMemory();
    }

    @Override
    public ConnectionPortDAO getConnectionPortDAO() {
        return new ConnectionPortDAOMemory();
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOMemory();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOMemory();
    }

    @Override
    public PcConfigurationDAO getPcConfigurationDAO() {
        return new PcConfigurationDAOMemory();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOMemory();
    }

    @Override
    public SimpleOrderLineDAO getSimpleOrderLineDAO() {
        return new SimpleOrderLineDAOMemory();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMemory();
    }
}
