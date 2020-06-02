package com.example.pcstore.catalog;

import com.example.pcstore.model.Client;

public interface CatalogView {
    void returnClient(Client client);
    void showStatus(String msg);
}
