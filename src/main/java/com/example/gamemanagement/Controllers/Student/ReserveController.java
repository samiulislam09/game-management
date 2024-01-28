package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.db.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    public ChoiceBox hour_choicebox;
    public ChoiceBox minute_choicebox;

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
        for (int minute = 0; minute < 60; minute += 1) {
            minuteList.add(minute);
        }
        minute_choicebox.setItems(minuteList);
    }

    public void reserve(){
        System.out.println(reservation_date.getValue());
        System.out.println(games_dropdown.getValue());
        System.out.println(hour_choicebox.getValue() + ":" + minute_choicebox.getValue());
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
