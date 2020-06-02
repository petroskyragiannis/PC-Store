package com.example.pcstore.catalog;

import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.memorydao.ProductDAOMemory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CatalogPresenterTest {
    CatalogPresenter presenter;
    CatalogViewStub view;

    @Before
    public void setup() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new CatalogViewStub();
        presenter = new CatalogPresenter();
        presenter.setView(view);
        presenter.setProductDAO(new ProductDAOMemory());
    }

}