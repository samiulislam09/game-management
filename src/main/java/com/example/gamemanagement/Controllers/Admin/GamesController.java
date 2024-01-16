package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Games;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GamesController {

    public TableView table_games;
    public TableColumn col_id;
    public TableColumn col_name;
    public TableColumn col_capacity;
    public TableColumn col_delete;
    public TextField input_name;
    public TextField input_capacity;
    public Button add_btn;
    public Button delete_btn;
    public Label error_label;

    public void onAdd(ActionEvent actionEvent) throws SQLException {
        String name = input_name.getText();
        String capacity = input_capacity.getText();
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO games(name, capacity) values (?, ?)");
        preparedStatement.setObject(1, name);
        preparedStatement.setObject(2, capacity);
        int err = preparedStatement.executeUpdate();
        if(err != 0){
            error_label.setText("Games added Successfully");
            input_name.setText("");
            input_capacity.setText("");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_label.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    public void onUpdate(ActionEvent actionEvent) {
    }

    public void onDelete(ActionEvent actionEvent) {
    }
}
