package com.example.pcstore.component;

import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;

public interface ComponentView {
    void returnPcConfiguration(PcConfiguration pcConfiguration, Component component);
    void showStatus(String msg);
}
