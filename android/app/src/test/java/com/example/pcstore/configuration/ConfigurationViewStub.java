package com.example.pcstore.configuration;

import com.example.pcstore.model.PcConfiguration;

public class ConfigurationViewStub implements ConfigurationView {

    PcConfiguration pcConfiguration;
    String status;

    @Override
    public void returnPcConfiguration(PcConfiguration pcConfiguration) {
        this.pcConfiguration = pcConfiguration;
    }

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public PcConfiguration getPcConfiguration() {
        return  pcConfiguration;
    }

    public String getStatus() {
        return status;
    }

}