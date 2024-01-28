package com.example.gamemanagement.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation extends Games {
    private final StringProperty id;
    private final StringProperty gameName;
    private final StringProperty reservationDate;
    private final StringProperty reservationTime;
    private final StringProperty reservedAt;


    public Reservation() {
        id = new SimpleStringProperty(this, "id");
        gameName = new SimpleStringProperty(this, "gameName");
        reservationDate = new SimpleStringProperty(this, "reservationDate");
        reservationTime = new SimpleStringProperty(this, "reservationTime");
        reservedAt = new SimpleStringProperty(this, "reservedAt");
    }


    public Reservation(String id, String gameName, String reservationDate, String reservationTime, String reservedAt) {
        this();
        setReservationId(id);
        setGameName(gameName);
        setReservationDate(reservationDate);
        setReservationTime(reservationTime);
        setReservedAt(reservedAt);
    }

    public StringProperty reservationIdProperty() { return id; }
    public String getReservationId() { return id.get(); }
    public void setReservationId(String newId) { id.set(newId); }
    public StringProperty gameNameProperty() { return gameName; }
    public String getGameName() { return gameName.get(); }
    public void setGameName(String newName) { gameName.set(newName); }
    public StringProperty reservationDateProperty() { return reservationDate; }
    public String getReservationDate() { return reservationDate.get(); }
    public void setReservationDate(String newDate) { reservationDate.set(newDate); }
    public StringProperty reservationTimeProperty() { return reservationTime; }
    public String getReservationTime() { return reservationTime.get(); }
    public void setReservationTime(String newTime) { reservationTime.set(newTime); }
    public StringProperty reservedAtProperty() { return reservedAt; }
    public String getReservedAt() { return reservedAt.get(); }
    public void setReservedAt(String newAt) { reservedAt.set(newAt); }

}
