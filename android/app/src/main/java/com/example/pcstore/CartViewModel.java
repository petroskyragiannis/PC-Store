package com.example.pcstore;

import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {
     CartPresenter presenter;

     public CartViewModel() {
         super();
         presenter = new CartPresenter();
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