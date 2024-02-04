package com.example.gamemanagement.Controllers;

import com.example.gamemanagement.Models.Model;
import com.example.gamemanagement.Views.AccountType;
import com.example.gamemanagement.db.DBconnection;
import com.example.gamemanagement.utils.UserInfo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField password_selector;
    public Button login_btn;
    public TextField usernameField;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(this::loginBtn);
    }

    private void onLogin() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);

        if(UserInfo.getInstance().getIsAdmin()){
            Model.getInstance().getViewFactory().showAdminWindow();
        }
        else{
            Model.getInstance().getViewFactory().showStudentWindow();
        }
    }

    public void toggleToSignUp(ActionEvent actionEvent) throws IOException {
        loadPage("/SignUp.fxml");

    }
    private void loadPage(String fxml) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource(fxml)).load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void loginBtn(ActionEvent actionEvent) {
        try{
            String username = usernameField.getText();
            String password = password_selector.getText();
            Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                UserInfo.getInstance().setUserId(resultSet.getString("id"));
                UserInfo.getInstance().setIsAdmin(resultSet.getBoolean("isAdmin"));
                onLogin();
            }
            else{
                error_lbl.setText("Wrong credentials");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
