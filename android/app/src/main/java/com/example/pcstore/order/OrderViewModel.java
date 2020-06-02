package com.example.pcstore.order;

import androidx.lifecycle.ViewModel;

import com.example.pcstore.memorydao.OrderDAOMemory;

public class OrderViewModel extends ViewModel {
    OrderPresenter presenter;

    public OrderViewModel() {
        super();
        presenter = new OrderPresenter();
        OrderDAOMemory orderDAOMemory = new OrderDAOMemory();
        presenter.setOrderDAO(orderDAOMemory);
    }

    public OrderPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.clearView();
    }
}
