package com.example.gamemanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {


    public TextField username_field;

    public void toggleToLogin(ActionEvent actionEvent) throws IOException {
        loadPage("/Login.fxml");
    }

    private void loadPage(String fxml) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource(fxml)).load();
        Stage stage = (Stage) username_field.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
