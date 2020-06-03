package com.example.pcstore.order;

public class OrderViewStub implements OrderView {

    String status;

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public String getStatus() {
        return status;
    }

}