package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Clients;
import com.example.gamemanagement.utils.Reservation;
import com.example.gamemanagement.utils.UserInfo;
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

public class ReservationListController implements Initializable {
    public TableColumn<Reservation, String> col_game_name;
    public TableColumn<Reservation, String> col_cancel_reservation;
    public Label error_label;
    public TableView<Reservation> table_reservation;
    public TableColumn<Reservation, String> col_reservation_date;
    public TableColumn<Reservation, String> col_reservation_slot;
    public TableColumn<Reservation, String> col_reserved_at;
    public Button btn_refresh;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();
        btn_refresh.setOnAction(event -> table());
    }

        public void table(){
            ObservableList<Reservation> list = FXCollections.observableArrayList();
            try {
                Connection connection = DBconnection.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservations WHERE reservationId = ? ");
                preparedStatement.setObject(1, UserInfo.getInstance().getUserId());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    list.add(new Reservation(resultSet.getString("id"), resultSet.getString("gameName"), resultSet.getString("reservationDate"), resultSet.getString("slot"), resultSet.getString("reservedAt")));
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            col_game_name.setCellValueFactory(param -> param.getValue().gameNameProperty());
            col_reservation_date.setCellValueFactory(param -> param.getValue().reservationDateProperty());
            col_reservation_slot.setCellValueFactory(param -> param.getValue().reservationTimeProperty());
            col_reserved_at.setCellValueFactory(param -> param.getValue().reservedAtProperty());
            table_reservation.setItems(list);
            col_cancel_reservation.setCellFactory(param -> new TableCell<>() {
                private final Button cancel = new Button("Cancel");
                {
                    cancel.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-max-width: 100px");
                }

                {
                    cancel.setOnAction(event -> {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Reservation");
                        alert.setHeaderText("Delete Reservation");
                        alert.setContentText("Are you sure you want to delete this reservation?");
                        Reservation reservation = getTableView().getItems().get(getIndex());
                        alert.showAndWait().ifPresent(response -> {
                            if (response == javafx.scene.control.ButtonType.OK) {
                                try {
                                    Connection connection = DBconnection.getInstance().getConnection();
                                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reservations WHERE id = ?");
                                    preparedStatement.setObject(1, reservation.getReservationId());
                                    preparedStatement.executeUpdate();
                                    error_label.setText("Reservation cancelled successfully");
                                    error_label.setStyle("-fx-text-fill: red");
                                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event1 -> error_label.setText("")));
                                    timeline.play();
                                    table();
                                } catch (SQLException throwable) {
                                    throwable.printStackTrace();
                                }
                            }
                        });
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

