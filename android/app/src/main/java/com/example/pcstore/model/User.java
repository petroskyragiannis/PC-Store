package com.example.pcstore.model;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    // Constructors
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name, String surname, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }


    //Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    private static Set<User> registeredUsers = new HashSet<>();

    public static Client register(String username, String password) throws RegisterException {
        for (User user : registeredUsers)
            if (user.getUsername().equalsIgnoreCase(username))
                throw new RegisterException("A user is already registered with username: " + username);
        if (password.length() < 8)
            throw new RegisterException("Password (" + password + ") must be at least 8 characters.");
        Client c = new Client(username, password);
        registeredUsers.add(c);
        c.login(username, password);
        return c;

    }

    public static User login(String username, String password) throws RegisterException {
        for (User user : registeredUsers)
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password))
                return user;
        throw new RegisterException("Username (" + username + ") and password (" + password + ")don't match.");

    }

    public static Client loginAsGuest() {
        return new Client("guest", "guest");
    }

    public static void addToRegisteredUsers(User user) {
        registeredUsers.add(user);
    }

    public static void removeFromRegisteredUsers(User user) {
        registeredUsers.remove(user);
    }
        private static class RegisterException extends Exception {
        public RegisterException(String message) {
            super(message);
        }
    }
    */
}