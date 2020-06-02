package com.example.pcstore.cart;

import androidx.lifecycle.ViewModel;
import com.example.pcstore.memorydao.ProductDAOMemory;

public class CartViewModel extends ViewModel {

     CartPresenter presenter;

     public CartViewModel() {
         super();
         presenter = new CartPresenter();
         ProductDAOMemory productDAOMemory = new ProductDAOMemory();
         presenter.setProductDAO(productDAOMemory);
     }

    public CartPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
         super.onCleared();
         presenter.clearView();
    }
}