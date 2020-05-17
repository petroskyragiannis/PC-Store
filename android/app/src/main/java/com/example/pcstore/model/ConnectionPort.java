package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class ConnectionPort implements Serializable {

    private Hardware name;
    private String description;

    // Constructors
    public ConnectionPort(Hardware name, String description) {
        this.name = name;
        this.description = description;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionPort)) return false;

        ConnectionPort that = (ConnectionPort) o;

        if (!name.equals(that.name)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    // Getters and Setters
    public Hardware getName() {
        return name;
    }

    public void setName(Hardware name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}