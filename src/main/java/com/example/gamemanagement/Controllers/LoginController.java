package com.example.gamemanagement.Controllers;

import com.example.gamemanagement.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox acc_selector;
    public TextField username_selector;
    public TextField password_selector;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onLoginBtnClick(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().showStudentWindow();
    }
}
