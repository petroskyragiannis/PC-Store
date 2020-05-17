package com.example.pcstore;

import androidx.lifecycle.ViewModel;
import com.example.pcstore.memorydao.ProductDAOMemory;

public class CatalogViewModel extends ViewModel {

    CatalogPresenter presenter;

    public CatalogViewModel() {
        super();
        presenter = new CatalogPresenter();
        ProductDAOMemory productDAOMemory = new ProductDAOMemory();
        presenter.setProductDAO(productDAOMemory);
    }

    public CatalogPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void  onCleared() {
        super.onCleared();
        presenter.clearView();
    }
}
