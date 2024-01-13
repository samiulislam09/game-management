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
        dashboard_btn.setOnAction(event -> onDashboard());
        addNewGame_btn.setOnAction(event -> onAddNewGame());
        clients_btn.setOnAction(event -> onClients());
        games_btn.setOnAction(event-> onGames());
        reservations_btn.setOnAction(event -> onReservations());


    }
    private void onAddNewGame(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ADD_NEW_GAME);
    }
    private void onClients(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }

    private void onDashboard(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DASHBOARD);
    }

    private void onGames(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.GAMES);
    }

    private void onReservations(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.RESERVATIONS);
    }

}
