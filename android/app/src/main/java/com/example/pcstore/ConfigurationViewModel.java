package com.example.pcstore;

import androidx.lifecycle.ViewModel;

public class ConfigurationViewModel extends ViewModel {

    ConfigurationPresenter presenter;

    public ConfigurationViewModel() {
        super();
        presenter = new ConfigurationPresenter();
    }

    public ConfigurationPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void  onCleared() {
        super.onCleared();
        presenter.clearView();
    }

}
