package com.example.pcstore;

import com.example.pcstore.model.PcConfiguration;

public interface ConfigurationView {
    void returnPcConfiguration(PcConfiguration pcConfiguration);
    void showStatus(String msg);
}
