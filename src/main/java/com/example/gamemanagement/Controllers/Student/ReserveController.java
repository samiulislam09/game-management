package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.db.DBconnection;
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

public class ReserveController implements Initializable {

    public ChoiceBox games_dropdown;
    public DatePicker reservation_date;
    public Button reserve_btn;
    public ChoiceBox slot_choice_box;
    public Label error_msg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropDownData();
        slots();
        reserve_btn.setOnAction(actionEvent -> reserve());
    }
    public void slots(){
        ObservableList list = FXCollections.observableArrayList();
        list.addAll("10:00-10:59", "11:00-11:59", "12:00-12:59", "13:00-13:59", "14:00-14:59", "15:00-15:59", "16:00-16:59");
        slot_choice_box.setItems(list);
    }

    public void reserve(){
        try {
            String slot = slot_choice_box.getValue().toString();
            slot = switch (slot) {
                case "10:00-10:59" -> "1";
                case "11:00-11:59" -> "2";
                case "12:00-12:59" -> "3";
                case "13:00-13:59" -> "4";
                case "14:00-14:59" -> "5";
                case "15:00-15:59" -> "6";
                case "16:00-16:59" -> "7";
                default -> slot;
            };
            String userId = UserInfo.getInstance().getUserId();
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM reservations WHERE reservationDate = ? AND slot = ? AND gameName = ?");
            preparedStatement1.setString(1, reservation_date.getValue().toString());
            preparedStatement1.setString(2, slot);
            preparedStatement1.setString(3, games_dropdown.getValue().toString());
            ResultSet resultSet = preparedStatement1.executeQuery();
            int count = 0;
            while (resultSet.next()){
                count++;
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM games WHERE name = ?");
            preparedStatement2.setString(1, games_dropdown.getValue().toString());
            ResultSet resultSet1 = preparedStatement2.executeQuery();
            int capacity = 0;
            while (resultSet1.next()){
                capacity = resultSet1.getInt("capacity");
            }

            PreparedStatement singlePlayerInfo = connection.prepareStatement("SELECT * FROM reservations WHERE reservationDate = ? AND slot = ? AND gameName = ? AND reservationId = ?");
            singlePlayerInfo.setString(1, reservation_date.getValue().toString());
            singlePlayerInfo.setString(2, slot);
            singlePlayerInfo.setString(3, games_dropdown.getValue().toString());
            singlePlayerInfo.setString(4, userId);
            ResultSet resultSet2 = singlePlayerInfo.executeQuery();
            int singlePlayerCount = 0;
            while (resultSet2.next()){
                singlePlayerCount++;
            }
            if (singlePlayerCount >= 1){
                error_msg.setText("You have already reserved this slot. You can only reserve a slot a single time!!!");
                error_msg.setStyle("-fx-text-fill: red");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_msg.setText("")));
                timeline.setCycleCount(1);
                timeline.play();
                return;
            }

            if (count >= capacity){
                error_msg.setText("Slot is full. Please select another slot!!!");
                error_msg.setStyle("-fx-text-fill: red");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_msg.setText("")));
                timeline.setCycleCount(1);
                timeline.play();
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservations (reservationId, gameName, reservationDate, slot) VALUES (?,?,?,?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, games_dropdown.getValue().toString());
            preparedStatement.setString(3, reservation_date.getValue().toString());
            preparedStatement.setString(4, slot);
            preparedStatement.executeUpdate();
            error_msg.setText("YAY Your reservation is created successfully");
            error_msg.setStyle("-fx-text-fill: green");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_msg.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        } catch (Exception e) {
            error_msg.setText("Something went wrong");
            error_msg.setStyle("-fx-text-fill: red");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), err -> error_msg.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    public void dropDownData(){
        ObservableList list = FXCollections.observableArrayList();
        try {
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM games");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(FXCollections.observableArrayList(resultSet.getString("name")));
                list.replaceAll(s -> s.toString().replaceAll("[\\[\\]]", ""));
            }
            games_dropdown.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
