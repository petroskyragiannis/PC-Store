package com.example.pcstore.model;

import java.io.Serializable;

public class Address implements Serializable {

    private String street;
    private String number;
    private String town;
    private String zipCode;

    // Constructors
    public Address() { }

    public Address(String street, String number, String town, String zipCode) {
        this.street = street;
        this.number = number;
        this.town = town;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!street.equals(address.street)) return false;
        if (!number.equals(address.number)) return false;
        if (!town.equals(address.town)) return false;
        return zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + town.hashCode();
        result = 31 * result + zipCode.hashCode();
        return result;
    }

    // Getters and Setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
