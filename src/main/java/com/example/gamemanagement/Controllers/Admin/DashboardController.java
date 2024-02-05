package com.example.gamemanagement.Controllers.Admin;

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
    public Label client_count;
    public Label remaining_slots;

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
            preparedStatement = connection.prepareStatement("SELECT * FROM games");
            resultSet = preparedStatement.executeQuery();
            int availableSlots = 0;
            while (resultSet.next()){
                int capacity = Integer.parseInt(resultSet.getString("capacity"));
                int boardQuantity = Integer.parseInt(resultSet.getString("boardQuantity"));
                availableSlots = availableSlots + (capacity * boardQuantity);
            }
            // get total number of games reserved
            preparedStatement = connection.prepareStatement("SELECT * FROM reservations");
            resultSet = preparedStatement.executeQuery();
            int remaining = 0;
            while (resultSet.next()){
                remaining++;
            }
            remaining_slots.setText(String.valueOf(availableSlots-remaining));

            // Get the total number of games reserved by the user
            preparedStatement = connection.prepareStatement("SELECT * FROM users where isAdmin IS NULL");
            resultSet = preparedStatement.executeQuery();
            int clientCount = 0;
            while (resultSet.next()){
                clientCount++;
            }
            available_games.setText(String.valueOf(availableGames));
            available_slot.setText(String.valueOf(availableSlots));
            client_count.setText(String.valueOf(clientCount));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
