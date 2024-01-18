package com.example.gamemanagement.utils;
import javafx.beans.property.*;
public class Games {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty capacity;

    public Games()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        capacity = new SimpleStringProperty(this, "capacity");
    }

    public Games(String id, String name, String capacity)
    {
        this();
        setId(id);
        setName(name);
        setCapacity(capacity);
    }


    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }
    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }
    public StringProperty capacityProperty() { return capacity; }
    public String getCapacity() { return capacity.get(); }
    public void setCapacity(String newCapacity) { capacity.set(newCapacity); }

}
