package com.example.gamemanagement.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Clients {
    private final StringProperty id;
    private final StringProperty username;

    public Clients() {
        id = new SimpleStringProperty(this, "id");
        username = new SimpleStringProperty(this, "name");
    }

    public Clients(String id, String name) {
        this();
        setId(id);
        setUsername(name);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }



    public void setId(String id) {
        this.id.set(id);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public ObservableValue<String> nameProperty() {
        return username;
    }
}
