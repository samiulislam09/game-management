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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReserveController implements Initializable {

    public ChoiceBox games_dropdown;
    public DatePicker reservation_date;
    public Button reserve_btn;
    public ChoiceBox hour_choicebox;
    public ChoiceBox minute_choicebox;
    public Label error_msg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropDownData();
        time();
        reserve_btn.setOnAction(actionEvent -> reserve());

    }
    public void time(){
        ObservableList<Integer> hourList = FXCollections.observableArrayList();
        ObservableList<Integer> minuteList = FXCollections.observableArrayList();
        for (int hour = 0; hour < 24; hour += 1) {
            hourList.add(hour);
        }
        hour_choicebox.setItems(hourList);
        for (int minute = 0; minute < 60; minute += 5) {
            minuteList.add(minute);
        }
        minute_choicebox.setItems(minuteList);
    }

    public void reserve(){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String userId = UserInfo.getInstance().getUserId();
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservations (reservationId, gameName, reservationDate, reservationTime,reservedAt) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, games_dropdown.getValue().toString());
            preparedStatement.setString(3, reservation_date.getValue().toString());
            preparedStatement.setString(4, hour_choicebox.getValue().toString() + ":" + minute_choicebox.getValue().toString());
            preparedStatement.setString(5, dtf.format(now));
            System.out.println(userId);
            preparedStatement.executeUpdate();
            error_msg.setText("Reservation Successful");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_msg.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        } catch (Exception e) {
            error_msg.setText("Something went wrong");
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
