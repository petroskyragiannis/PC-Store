package com.example.pcstore;

import androidx.lifecycle.ViewModel;
import com.example.pcstore.memorydao.ProductDAOMemory;

public class ProductViewModel extends ViewModel {

    ProductPresenter presenter;

    public ProductViewModel() {
        super();
        presenter = new ProductPresenter();
        ProductDAOMemory productDAOMemory = new ProductDAOMemory();
        presenter.setProductDAO(productDAOMemory);
    }

    public ProductPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void  onCleared() {
        super.onCleared();
        presenter.clearView();
    }
}
