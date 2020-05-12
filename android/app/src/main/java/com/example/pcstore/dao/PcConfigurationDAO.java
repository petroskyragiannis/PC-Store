package com.example.pcstore.dao;

import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;
import java.util.List;

public interface PcConfigurationDAO {

    void save(PcConfiguration entity);

    void delete(PcConfiguration entity);

    List<PcConfiguration> findAll();

    PcConfiguration find(Component... components);
}