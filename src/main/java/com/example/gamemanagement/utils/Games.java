package com.example.gamemanagement.utils;
import javafx.beans.property.*;
public class Games {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final IntegerProperty capacity = new SimpleIntegerProperty(this, "capacity");

    public Games(int id, String name, int capacity) {
        setId(id);
        setName(name);
        setCapacity(capacity);
    }

    // Getter and setter methods for id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Getter and setter methods for name
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Getter and setter methods for capacity
    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }
}
