package com.example.pcstore;

import androidx.lifecycle.ViewModel;
import com.example.pcstore.memorydao.UserDAOMemory;

public class RegisterViewModel extends ViewModel {

    RegisterPresenter presenter;

    public RegisterViewModel() {
        super();
        presenter = new RegisterPresenter();
        UserDAOMemory userDAOMemory = new UserDAOMemory();
        presenter.setUserDAO(userDAOMemory);
    }

    public RegisterPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.clearView();
    }

}
