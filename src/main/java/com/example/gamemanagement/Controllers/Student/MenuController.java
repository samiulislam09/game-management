package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button profile_btn;
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
            Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set("ReservationList");
        });
        dashboard_btn.setOnAction(event->{
            Model.getInstance().getViewFactory().getStudentSelectedMenuItem().set("Dashboard");
        });
    }
}
