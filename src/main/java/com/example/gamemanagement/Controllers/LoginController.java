package com.example.gamemanagement.Controllers;

import com.example.gamemanagement.Models.Model;
import com.example.gamemanagement.Views.AccountType;
import javafx.collections.FXCollections;
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
    public ChoiceBox<AccountType> acc_selector;
    public TextField username_selector;
    public TextField password_selector;
    public Label error_label;
    public Button login_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.STUDENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.STUDENT){
            Model.getInstance().getViewFactory().showStudentWindow();
        }
        else{
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
}
