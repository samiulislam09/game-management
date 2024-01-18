package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Games;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GamesController implements Initializable {

    public TableView<Games> table_games;
    public TableColumn<Games, String> col_id;
    public TableColumn<Games, String> col_name;
    public TableColumn<Games, String> col_capacity;
    public TableColumn col_delete;
    public TextField input_name;
    public TextField input_capacity;
    public Button add_btn;
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
            table();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_label.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    public void table(){
        ObservableList<Games> list = FXCollections.observableArrayList();
        try {
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM games");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Games(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("capacity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_id.setCellValueFactory(data -> data.getValue().idProperty());
        col_name.setCellValueFactory(data -> data.getValue().nameProperty());
        col_capacity.setCellValueFactory(data -> data.getValue().capacityProperty());
        table_games.setItems(list);

        // add a delete button to each row
        col_delete.setCellFactory(param -> new TableCell<Games, String>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                // ensure that the cell is created only on non-empty rows
                if (empty) {
                    setGraphic(null);
                } else {
                    deleteButton.setOnAction(event -> {
                        Games games = getTableView().getItems().get(getIndex());
                        try {
                            Connection connection = DBconnection.getInstance().getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM games WHERE id = ?");
                            preparedStatement.setObject(1, games.getId());
                            int err = preparedStatement.executeUpdate();
                            if(err != 0){
                                error_label.setText("Games deleted Successfully");
                                table();
                                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_label.setText("")));
                                timeline.setCycleCount(1);
                                timeline.play();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                    setGraphic(deleteButton);
                }
            }
        });
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();
    }
}
