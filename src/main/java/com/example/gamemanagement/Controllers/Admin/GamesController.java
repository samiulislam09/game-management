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
    public TableColumn<Games, String> col_name;
    public TableColumn<Games, String> col_capacity;
    public TableColumn col_delete;
    public TextField input_name;
    public TextField input_capacity;
    public Button add_btn;
    public Label error_label;
    public TextField board_quantity;
    public TableColumn<Games, String> col_board_quantity;
    public Button update_btn;


    public void onAdd(ActionEvent actionEvent) throws SQLException {
        if(input_name.getText().isEmpty() || input_capacity.getText().isEmpty() || board_quantity.getText().isEmpty()){
            error_label.setText("Please fill all the fields!!");
            {
                error_label.setStyle("-fx-text-fill: red");
            }
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_label.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
            return;
        }
        String name = input_name.getText();
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO games(name, capacity, boardQuantity) values (?, ?, ?)");
        preparedStatement.setObject(1, name);
        preparedStatement.setObject(2, input_capacity.getText());
        preparedStatement.setObject(3, board_quantity.getText());

        int err = preparedStatement.executeUpdate();

        if(err != 0){
            error_label.setText("Games added Successfully");
            input_name.setText("");
            input_capacity.setText("");
            board_quantity.setText("");
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
                list.add(new Games(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("capacity"), resultSet.getString("boardQuantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_name.setCellValueFactory(data -> data.getValue().nameProperty());
        col_capacity.setCellValueFactory(data -> data.getValue().capacityProperty());
        col_board_quantity.setCellValueFactory(data -> data.getValue().boardQuantityProperty());
        table_games.setItems(list);
        col_delete.setCellFactory(param -> new TableCell<Games, String>() {
            private final Button deleteButton = new Button("Delete");
                {
                    deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-max-width: 100px");
                }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    deleteButton.setOnAction(event -> {
                        Games games = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Delete Game");
                        alert.setContentText("Are you sure you want to delete the game?");
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
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
                            }
                        });

                    });
                    setGraphic(deleteButton);
                }
            }
        });
        table_games.setOnMouseClicked(event -> {
            Games games = table_games.getSelectionModel().getSelectedItem();
            input_name.setText(games.getName());
            input_capacity.setText(games.getCapacity());
            board_quantity.setText(games.getBoardQuantity());
        });
        update_btn.setOnAction(event -> {
            try {
                int err = getErr();
                if(err != 0){
                    error_label.setText("Games updated Successfully");
                    table();
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> error_label.setText("")));
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }

    private int getErr() throws SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE games SET name = ?, capacity = ?, boardQuantity = ? WHERE name = ?");
        preparedStatement.setObject(1, input_name.getText());
        preparedStatement.setObject(2, input_capacity.getText());
        preparedStatement.setObject(3, board_quantity.getText());
        preparedStatement.setObject(4, input_name.getText());
        int err = preparedStatement.executeUpdate();
        return err;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();
    }
}
