package com.example.pcstore.model;

public class Employee extends User {

    private boolean admin;

    // Constructors
    public Employee(String username, String password) {
        super(username, password);
        this.admin=true;
    }

    public Employee(String username, String password, String name, String surname, String phoneNumber, String email) {
        super(username, password, name, surname, phoneNumber, email);
        this.admin=true;
    }
// Getter and Setter

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}