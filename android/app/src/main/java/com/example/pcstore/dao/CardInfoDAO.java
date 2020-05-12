package com.example.pcstore.dao;

import com.example.pcstore.model.CardInfo;

import java.util.List;

public interface CardInfoDAO {

    void save(CardInfo entity);

    void delete(CardInfo entity);

    List<CardInfo> findAll();

    CardInfo find(String number);

}
