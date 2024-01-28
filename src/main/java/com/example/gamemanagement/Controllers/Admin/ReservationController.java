package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Reservation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
//    public TableColumn<Reservation, String> col_id;
    public TableColumn<Reservation, String> col_game_name;
    public TableColumn<Reservation, String> col_cancel_reservation;
    public Label error_label;
    public TableView<Reservation> table_reservation;
    public TableColumn<Reservation, String> col_reservation_date;
    public TableColumn<Reservation, String> col_reservation_time;
    public TableColumn<Reservation, String> col_reserved_at;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();
    }

    public void table(){
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        try {
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservations");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Reservation(resultSet.getString("id"), resultSet.getString("gameName"), resultSet.getString("reservationDate"), resultSet.getString("reservationTime"), resultSet.getString("reservedAt")));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        col_game_name.setCellValueFactory(param -> param.getValue().gameNameProperty());
        col_reservation_date.setCellValueFactory(param -> param.getValue().reservationDateProperty());
        col_reservation_time.setCellValueFactory(param -> param.getValue().reservationTimeProperty());
        col_reserved_at.setCellValueFactory(param -> param.getValue().reservedAtProperty());
        table_reservation.setItems(list);
        col_cancel_reservation.setCellFactory(param -> new TableCell<>() {
            private final Button cancel = new Button("Cancel");


            {
                cancel.setOnAction(event -> {
                    Reservation reservation = getTableView().getItems().get(getIndex());
                    try {
                        Connection connection = DBconnection.getInstance().getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reservations WHERE id = ?");
                        preparedStatement.setObject(1, reservation.getReservationId());
                        preparedStatement.executeUpdate();
                        error_label.setText("Reservation cancelled successfully");
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event1 -> error_label.setText("")));
                        timeline.play();
                        table();
                    } catch (SQLException throwable) {
                        throwable.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                if (b) {
                    setGraphic(null);
                } else {
                    setGraphic(cancel);
                }
            }
        });
    }


}

