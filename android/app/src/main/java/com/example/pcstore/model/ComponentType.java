package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class ComponentType implements Serializable {

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
        if (o == null || getClass() != o.getClass()) return false;
        ComponentType that = (ComponentType) o;
        return required == that.required &&
                name == that.name;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, required);
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