package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.UserInfo;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Label available_games;
    public Label available_slot;
    public Label reserved_by_me;
    public Label available_game;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection connection = DBconnection.getInstance().getConnection();
            // Get the total number of games
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM games");
            ResultSet resultSet = preparedStatement.executeQuery();
            int availableGames = 0;
            while (resultSet.next()){
                availableGames++;
            }
            // Get the total number of slots
            preparedStatement = connection.prepareStatement("SELECT * FROM reservations");
            resultSet = preparedStatement.executeQuery();
            int availableSlots = 0;
            while (resultSet.next()){
                availableSlots++;
            }
            // Get the total number of games reserved by the user
            preparedStatement = connection.prepareStatement("SELECT * FROM reservations WHERE reservationId = ?");
            preparedStatement.setObject(1, UserInfo.getInstance().getUserId());
            resultSet = preparedStatement.executeQuery();
            int reservedByMe = 0;
            while (resultSet.next()){
                reservedByMe++;
            }
            available_games.setText(String.valueOf(availableGames));
            available_slot.setText(String.valueOf(availableSlots));
            reserved_by_me.setText(String.valueOf(reservedByMe));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
