package com.example.pcstore.model;

public class ComponentType {

    private Hardware name;
    private boolean required;

    // Constructors
    public ComponentType() {
    }

    public ComponentType(Hardware name, boolean required) {
        this.name = name;
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComponentType)) return false;

        ComponentType that = (ComponentType) o;

        if (required != that.required) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (required ? 1 : 0);
        return result;
    }

    // Getters and Setters
    public Hardware getName() {
        return name;
    }

    public void setName(Hardware name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

}