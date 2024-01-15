package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.Models.Model;
import com.example.gamemanagement.Views.StudentMenuOptions;
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
    public Button reservation_btn;
    public Button logOut_btn;
    public Button reserve_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        reservation_btn.setOnAction(event-> {
            Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set(StudentMenuOptions.RESERVATION);
        });
        dashboard_btn.setOnAction(event->{
            Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set(StudentMenuOptions.DASHBOARD);
        });
        reserve_btn.setOnAction(event->{
            Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set(StudentMenuOptions.RESERVE);
        });
        logOut_btn.setOnAction(event -> {
            try {
                loadPage("/Login.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadPage(String fxml) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource(fxml)).load();
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
