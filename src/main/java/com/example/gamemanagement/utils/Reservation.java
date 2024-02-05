package com.example.gamemanagement.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation extends Games {
    private final StringProperty user;
    private final StringProperty id;
    private final StringProperty gameName;
    private final StringProperty reservationDate;
    private final StringProperty reservationTime;
    private final StringProperty reservedAt;

    public Reservation() {
        user = new SimpleStringProperty(this, "user");
        id = new SimpleStringProperty(this, "id");
        gameName = new SimpleStringProperty(this, "gameName");
        reservationDate = new SimpleStringProperty(this, "reservationDate");
        reservationTime = new SimpleStringProperty(this, "slot");
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

    public void setUser(String newUser) { user.set(newUser); }
    public String getReservationId() { return id.get(); }
    public void setReservationId(String newId) { id.set(newId); }
    public StringProperty gameNameProperty() { return gameName; }
    public void setGameName(String newName) { gameName.set(newName); }
    public StringProperty reservationDateProperty() { return reservationDate; }
    public void setReservationDate(String newDate) { reservationDate.set(newDate); }
    public StringProperty reservationTimeProperty() {
        return switch (reservationTime.get()) {
            case "1" -> new SimpleStringProperty("10:00-10:59[slot 1]");
            case "2" -> new SimpleStringProperty("11:00-11:59[slot 2]");
            case "3" -> new SimpleStringProperty("12:00-12:59[slot 3]");
            case "4" -> new SimpleStringProperty("13:00-13:59[slot 4]");
            case "5" -> new SimpleStringProperty("14:00-14:59[slot 5]");
            case "6" -> new SimpleStringProperty("15:00-15:59[slot 6]");
            case "7" -> new SimpleStringProperty("16:00-16:59[slot 7]");
            default -> new SimpleStringProperty("Invalid time[slot 8]");
        };
    }
    public void setReservationTime(String newTime) { reservationTime.set(newTime); }
    public StringProperty reservedAtProperty() { return reservedAt; }
    public void setReservedAt(String newAt) { reservedAt.set(newAt); }

}
