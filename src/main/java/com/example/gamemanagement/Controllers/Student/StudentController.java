package com.example.gamemanagement.Controllers.Student;

import com.example.gamemanagement.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    public BorderPane student_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getStudentSelectedMenuItem().addListener((observable, oldVal, newVal) -> {
            switch (newVal){
                case RESERVATION -> student_parent.setCenter(Model.getInstance().getViewFactory().getReservationView());
                case RESERVE -> student_parent.setCenter(Model.getInstance().getViewFactory().getReserveView());
                default -> student_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
