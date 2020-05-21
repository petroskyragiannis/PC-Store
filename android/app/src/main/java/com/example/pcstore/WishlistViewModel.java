package com.example.pcstore;

import androidx.lifecycle.ViewModel;

public class WishlistViewModel extends ViewModel {

    WishlistPresenter presenter;

    public WishlistViewModel() {
        super();
        presenter = new WishlistPresenter();
    }

    public WishlistPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void  onCleared() {
        super.onCleared();
        presenter.clearView();
    }
}
