package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Games;
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

public class ReservationListController implements Initializable {
    public TableColumn<Reservation, String> col_id;
    public TableColumn<Reservation, String> col_game_name;
    public TableColumn<Reservation, String> col_start_time;
    public TableColumn<Reservation, String> col_ending_time;
    public TableColumn<Reservation, String> col_cancel_reservation;
    public Label error_label;
    public TableView<Reservation> table_reservation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {






    }


        // make a table view of reservations for the student to see his reservations and cancel them if he wants to
        public void table(){
            ObservableList<Reservation> list = FXCollections.observableArrayList();
            try {
                Connection connection = DBconnection.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservation");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    list.add(new Reservation(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("start_time"), resultSet.getString("ending_time")));
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            col_id.setCellValueFactory(data -> data.getValue().idProperty());
            col_game_name.setCellValueFactory(data -> data.getValue().nameProperty());
            col_start_time.setCellValueFactory(data -> data.getValue().startTimeProperty());
            col_ending_time.setCellValueFactory(data -> data.getValue().endingTimeProperty());
            col_cancel_reservation.setCellValueFactory(data -> data.getValue().cancelReservationProperty());
            table_reservation.setItems(list);
            col_cancel_reservation.setCellFactory(param -> new TableCell<Reservation, String>() {
                private final Button cancel_btn = new Button("Cancel");
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    if (b) {
                        setGraphic(null);
                    } else {
                        setGraphic(cancel_btn);
                        cancel_btn.setOnAction(event -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            try {
                                Connection connection = DBconnection.getInstance().getConnection();
                                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reservation WHERE id = ?");
                                preparedStatement.setObject(1, reservation.getId());
                                int err = preparedStatement.executeUpdate();
                                if(err != 0){
                                    error_label.setText("Reservation canceled Successfully");
                                    table();
                                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_label.setText("")));
                                    timeline.setCycleCount(1);
                                    timeline.play();
                                }
                            } catch (SQLException throwable) {
                                throwable.printStackTrace();
                            }
                        });
                    }
                }
            });
        }


    }

