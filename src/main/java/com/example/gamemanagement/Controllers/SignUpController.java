package com.example.gamemanagement.Controllers;

import com.example.gamemanagement.db.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {


    public TextField username_field;
    public PasswordField password_field;
    public Label error_lbl;



    public void toggleToLogin(ActionEvent actionEvent) throws IOException {
        loadPage("/Login.fxml");
    }

    private void loadPage(String fxml) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource(fxml)).load();
        Stage stage = (Stage) username_field.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


    public void signUpBtn(ActionEvent actionEvent) throws SQLException {
        String username = username_field.getText();
        String password = password_field.getText();
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(username, password) values (?, ?)");
        preparedStatement.setObject(1, username);
        preparedStatement.setObject(2, password);

        int err = preparedStatement.executeUpdate();
        if(err != 0){
            System.out.println("User created successfully");
        }
    }
}
