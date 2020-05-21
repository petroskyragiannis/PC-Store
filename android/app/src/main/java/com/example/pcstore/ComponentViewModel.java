package com.example.pcstore;

import androidx.lifecycle.ViewModel;
import com.example.pcstore.memorydao.ProductDAOMemory;

public class ComponentViewModel extends ViewModel {

    ComponentPresenter presenter;

    public ComponentViewModel() {
        super();
        presenter = new ComponentPresenter();
        ProductDAOMemory productDAOMemory = new ProductDAOMemory();
        presenter.setProductDAO(productDAOMemory);
    }

    public ComponentPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void  onCleared() {
        super.onCleared();
        presenter.clearView();
    }

}