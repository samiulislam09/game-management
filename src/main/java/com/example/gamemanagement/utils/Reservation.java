package com.example.gamemanagement.utils;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Reservation extends Games {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty StartTime;
    private final StringProperty EndingTime;

    public Reservation()
    {
        id = null;
        name = null;
        StartTime = null;
        EndingTime = null;
    }

    public Reservation(StringProperty id, StringProperty name, StringProperty startTime, StringProperty endingTime) {
        this.id = id;
        this.name = name;
        StartTime = startTime;
        EndingTime = endingTime;
    }

    public Reservation(String id, String name, String startTime, String endingTime) {
        this.id = null;
        this.name = null;
        StartTime = null;
        EndingTime = null;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }


    public StringProperty startTimeProperty() {
        return StartTime;
    }


    public StringProperty endingTimeProperty() {
        return EndingTime;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableValue<String> cancelReservationProperty() {
        return null;
    }
}
