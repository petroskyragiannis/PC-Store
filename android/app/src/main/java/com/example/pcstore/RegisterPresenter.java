package com.example.pcstore;

import com.example.pcstore.dao.UserDAO;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Employee;
import com.example.pcstore.model.User;

public class RegisterPresenter {

    private RegisterView view;
    private UserDAO userDAO;

    public RegisterPresenter() {}

    public Client register(String username, String password) {
        User user = userDAO.find(username);
        if (user!=null) {
            view.showStatus("A user already exists with this username.");
            return null;
        }
        if (password.length()<8) {
            view.showStatus("Password must be at least 8 characters.");
            return null;
        }
        Client client = new Client(username,password);
        userDAO.save(client);
        view.showStatus(username+" registered successfully.");
        return client;
    }

    public User login(String username, String password) {
        User user = userDAO.find(username);
        if (user == null) {
            view.showStatus("No user found.");
            return null;
        }
        if (user.getPassword().equals(password)) {
            if (user instanceof Client)
                view.showStatus("Client " + username + " logged in successfully.");
            else if (user instanceof Employee)
                view.showStatus("Employee " + username + " logged in successfully.");
            return user;
        } else {
            view.showStatus("Wrong password.");
            return null;
        }
    }

    public Client loginAsGuest() {
        view.showStatus("Logged in as guest.");
        return new Client("guest", "guest");
    }

    public void setView(RegisterView view) {
        this.view = view;
    }

    public void clearView() {
        this.view = null;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
