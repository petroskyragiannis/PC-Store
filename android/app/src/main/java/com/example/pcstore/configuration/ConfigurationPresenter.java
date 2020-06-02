package com.example.pcstore.configuration;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.pcstore.model.PcConfiguration;

public class ConfigurationPresenter {

    private ConfigurationView view;
    private String[] categories;

    public ConfigurationPresenter() {
        categories = new String[]{
                "Select a Case",
                "Select a CPU",
                "Select a Motherboard",
                "Select a RAM",
                "Select a GPU",
                "Select a Hard Drive",
                "Select a PSU",
                "Select a Mouse",
                "Select a Keyboard",
                "Select a Monitor"
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean checkPcConfiguration(PcConfiguration config) {
        try {
            if (config.checkRequirements())
                return config.checkCompatibility();
        } catch (PcConfiguration.CompatibilityException e) {
            view.showStatus(e.getMessage());
        }
        return false;
    }

    public void returnPcConfiguration(PcConfiguration pcConfiguration) {
        view.returnPcConfiguration(pcConfiguration);
    }

    public String[] getCategories() {
        return categories;
    }

    public void setView(ConfigurationView view) {
        this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

}