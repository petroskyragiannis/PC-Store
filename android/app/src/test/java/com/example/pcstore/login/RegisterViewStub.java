package com.example.pcstore.login;

public class RegisterViewStub implements RegisterView {

    String username;
    String password;
    String status;

    @Override
    public void returnCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void showStatus(String msg) {
        status = msg;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

}