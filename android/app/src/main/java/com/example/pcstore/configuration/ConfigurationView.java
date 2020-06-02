package com.example.pcstore.configuration;

import com.example.pcstore.model.PcConfiguration;

public interface ConfigurationView {
    void returnPcConfiguration(PcConfiguration pcConfiguration);
    void showStatus(String msg);
}
