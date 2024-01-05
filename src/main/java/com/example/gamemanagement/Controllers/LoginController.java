package com.example.gamemanagement.Controllers;

import com.example.gamemanagement.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox acc_selector;
    public TextField username_selector;
    public TextField password_selector;
    public Label error_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onLoginBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showStudentWindow();
    }
}
