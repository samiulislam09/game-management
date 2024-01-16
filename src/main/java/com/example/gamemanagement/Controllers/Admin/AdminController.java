package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case GAMES -> admin_parent.setCenter(Model.getInstance().getViewFactory().getGamesView());
                case RESERVATIONS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminReservationView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().showAdminDashboardView());
            }
        });
    }
}
