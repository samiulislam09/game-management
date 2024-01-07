package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.Models.Model;
import com.example.gamemanagement.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button dashboard_btn;
    public Button games_btn;
    public Button reservations_btn;
    public Button clients_btn;
    public Button logout_btn;
    public Button addNewGame_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    addListeners();
    }

    public void addListeners(){

    }
    public void onAddNewGame(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ADD_NEW_GAME);
    }
}
