package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.Clients;
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

public class ClientsController implements Initializable {
    public TableView<Clients> Users_list;
    public TableColumn<Clients, String> col_id;
    public TableColumn<Clients, String> col_username;
    public TableColumn<Clients, String> col_delete;
    public Label error_label;
    public TableColumn col_update;

    public void table(){
        ObservableList<Clients> list = FXCollections.observableArrayList();
        try {
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where isadmin IS NULL");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Clients(resultSet.getString("id"), resultSet.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_id.setCellValueFactory(data -> data.getValue().idProperty());
        col_username.setCellValueFactory(data -> data.getValue().nameProperty());
        Users_list.setItems(list);
        col_delete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            {
                deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-max-width: 100px");
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Client");
                    alert.setContentText("Are you sure you want to delete this client?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == javafx.scene.control.ButtonType.OK) {
                            Clients client = getTableView().getItems().get(getIndex());
                            Users_list.getItems().remove(client);
                            try {
                                Connection connection = DBconnection.getInstance().getConnection();
                                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
                                preparedStatement.setString(1, client.getId());
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                });
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();

    }
}
