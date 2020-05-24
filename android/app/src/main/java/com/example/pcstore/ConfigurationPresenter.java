package com.example.pcstore;

import com.example.pcstore.model.PcConfiguration;

public class ConfigurationPresenter {

        private ConfigurationView view;

        public ConfigurationPresenter() {}

        public void returnPcConfiguration(PcConfiguration pcConfiguration) {
            view.returnPcConfiguration(pcConfiguration);
        }

        public void setView(ConfigurationView view) {
            this.view = view;
        }

        public void clearView(){
            this.view = null;
        }

    }