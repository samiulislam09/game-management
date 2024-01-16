package com.example.gamemanagement.Controllers.Admin;

import com.example.gamemanagement.Models.Model;
import com.example.gamemanagement.Views.AdminMenuOptions;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button dashboard_btn;
    public Button games_btn;
    public Button reservations_btn;
    public Button clients_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    addListeners();
    }

    public void addListeners(){
        dashboard_btn.setOnAction(event -> onDashboard());
        clients_btn.setOnAction(event -> onClients());
        games_btn.setOnAction(event-> onGames());
        reservations_btn.setOnAction(event -> onReservations());
        logout_btn.setOnAction(event -> {
            try {
                loadPage("/Login.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


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

    private void loadPage(String fxml) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource(fxml)).load();
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
