package com.example.pcstore.memorydao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.pcstore.dao.PcConfigurationDAO;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;

import java.util.ArrayList;
import java.util.List;

public class PcConfigurationDAOMemory implements PcConfigurationDAO {
    protected static ArrayList<PcConfiguration> entities = new ArrayList<PcConfiguration>();

    @Override
    public void save(PcConfiguration entity) {
        entities.add(entity);
    }

    @Override
    public void delete(PcConfiguration entity) {
        entities.remove(entity);
    }

    @Override
    public List<PcConfiguration> findAll() {
        return new ArrayList<PcConfiguration>(entities);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public PcConfiguration find(Component... components) {
        for (PcConfiguration pcConfiguration: entities)
            for (Component component: components)
                if (containsComponent(pcConfiguration,component))
                    return pcConfiguration;
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean containsComponent(PcConfiguration pcConfiguration, Component component) {
        for (Component pcComponent: pcConfiguration.getComponents())
            if (pcComponent.equals(component))
                return true;
        return false;
    }
}
