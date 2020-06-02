package com.example.pcstore.catalog_update;

import androidx.lifecycle.ViewModel;
import com.example.pcstore.memorydao.ProductDAOMemory;

public class CatalogUpdateViewModel extends ViewModel {

    CatalogUpdatePresenter presenter;

    public CatalogUpdateViewModel() {
        super();
        presenter = new CatalogUpdatePresenter();
        ProductDAOMemory productDAOMemory = new ProductDAOMemory();
        presenter.setProductDAO(productDAOMemory);
    }

    public CatalogUpdatePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void  onCleared() {
        super.onCleared();
        presenter.clearView();
    }

}