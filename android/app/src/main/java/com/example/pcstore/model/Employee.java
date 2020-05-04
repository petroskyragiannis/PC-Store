package com.example.pcstore.model;

public class Employee extends User {

    private boolean admin;

    // Constructors

    public Employee(String username, String password) {
        super(username, password);
        this.admin=true;
        User.addToRegisteredUsers(this);
    }

    // Getter and Setter

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}