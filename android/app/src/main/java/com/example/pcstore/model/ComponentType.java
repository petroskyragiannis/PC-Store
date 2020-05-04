package com.example.pcstore.model;

public class ComponentType {

    private String name;
    private boolean required;

    // Constructors
    public ComponentType() {
    }

    public ComponentType(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentType)) return false;

        ComponentType that = (ComponentType) o;

        if (required != that.required) return false;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (required ? 1 : 0);
        return result;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

}