package com.example.pcstore.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Component extends Product {

    private ComponentType type = new ComponentType();
    private Set<ConnectionPort> providedPorts = new HashSet<>();
    private Set<ConnectionPort> requiredPorts = new HashSet<>();

    // Constructors
    public Component(int id, String name, int stock, int price, Hardware category) {
        super(id, name, stock, price, category);
    }

    public Component(int id, String name, int stock, int price, Hardware category, ComponentType type, Set<ConnectionPort> providedPorts, Set<ConnectionPort> requiredPorts) {
        super(id, name, stock, price, category);
        this.type = type;
        this.providedPorts = providedPorts;
        this.requiredPorts = requiredPorts;
    }

    // ConnectionPort Methods
    public void addToProvidedPorts(ConnectionPort port) {
        providedPorts.add(port);
    }

    public void removeFromProvidedPorts(ConnectionPort port) {
        providedPorts.remove(port);
    }

    public void addToRequiredPorts(ConnectionPort port) {
        requiredPorts.add(port);
    }

    public void removeFromRequiredPorts(ConnectionPort port) {
        requiredPorts.remove(port);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        if (!super.equals(o)) return false;

        Component component = (Component) o;

        if (!type.equals(component.type)) return false;
        if (!Objects.equals(providedPorts, component.providedPorts)) return false;
        return Objects.equals(requiredPorts, component.requiredPorts);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result +  type.hashCode();
        result = 31 * result + (providedPorts != null ? providedPorts.hashCode() : 0);
        result = 31 * result + (requiredPorts != null ? requiredPorts.hashCode() : 0);
        return result;
    }

    // Getters and Setters
    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public Set<ConnectionPort> getProvidedPorts() {
        return providedPorts;
    }

    public void setProvidedPorts(Set<ConnectionPort> providedPorts) {
        this.providedPorts = providedPorts;
    }

    public Set<ConnectionPort> getRequiredPorts() {
        return requiredPorts;
    }

    public void setRequiredPorts(Set<ConnectionPort> requiredPorts) {
        this.requiredPorts = requiredPorts;
    }

}
