package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Clients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReserveController implements Initializable {

    public ChoiceBox games_dropdown;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropDownData();
    }

    public void dropDownData(){
        ObservableList list = FXCollections.observableArrayList();
        try {

            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM games");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(FXCollections.observableArrayList(resultSet.getString("name")));
            }
            games_dropdown.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
