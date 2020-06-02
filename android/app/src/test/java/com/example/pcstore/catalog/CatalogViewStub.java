package com.example.pcstore.catalog;

import com.example.pcstore.model.Client;

public class CatalogViewStub implements CatalogView {

    Client client;
    String status;

    @Override
    public void returnClient(Client client) {
        this.client = client;
    }

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public Client getClient() {
        return client;
    }

    public String getStatus() {
        return status;
    }

}