package com.example.pcstore.component;

import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;

public class ComponentViewStub implements ComponentView {

    PcConfiguration pcConfiguration;
    Component component;
    String status;

    @Override
    public void returnPcConfiguration(PcConfiguration pcConfiguration, Component component) {
        this.pcConfiguration = pcConfiguration;
        this.component = component;
    }

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public PcConfiguration getPcConfiguration() {
        return pcConfiguration;
    }

    public Component getComponent() {
        return component;
    }

    public String getStatus() {
        return status;
    }

}