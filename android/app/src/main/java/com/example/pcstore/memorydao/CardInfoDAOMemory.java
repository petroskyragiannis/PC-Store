package com.example.pcstore.memorydao;

import com.example.pcstore.dao.CardInfoDAO;
import com.example.pcstore.model.CardInfo;

import java.util.ArrayList;
import java.util.List;

public class CardInfoDAOMemory implements CardInfoDAO {
    protected static ArrayList<CardInfo> entities = new ArrayList<CardInfo>();

    @Override
    public void save(CardInfo entity) {
        entities.add(entity);
    }

    @Override
    public void delete(CardInfo entity) {
        entities.remove(entity);
    }

    @Override
    public List<CardInfo> findAll() {
        return new ArrayList<CardInfo>(entities);

    }

    @Override
    public CardInfo find(String cardNumber) {
        for (CardInfo card: entities)
            if (card.getCardNumber().equalsIgnoreCase(cardNumber))
                return card;
        return null;
    }
}
