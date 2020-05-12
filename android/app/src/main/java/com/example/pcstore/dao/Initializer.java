package com.example.pcstore.dao;

public abstract class Initializer {

    protected abstract void eraseData();

    public abstract AddressDAO getAddressDAO();
    public abstract CardInfoDAO getCardInfoDAO();
    public abstract ClientDAO getClientDAO();
    public abstract ComponentDAO getComponentDAO();
    public abstract ComponentTypeDAO getComponentTypeDAO();
    public abstract ConnectionPortDAO getConnectionPortDAO();
    public abstract EmployeeDAO getEmployeeDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract PcConfigurationDAO getPcConfigurationDAO();
    public abstract ProductDAO getProductDAO();
    public abstract SimpleOrderLineDAO getSimpleOrderLineDAO();
    public abstract UserDAO getUserDAO();
}
