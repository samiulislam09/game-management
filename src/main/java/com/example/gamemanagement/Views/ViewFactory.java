package com.example.gamemanagement.Views;

import com.example.gamemanagement.Controllers.Student.StudentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class ViewFactory {
    //Student views
    private AnchorPane dashboardView;
    public ViewFactory(){}

    public AnchorPane getDashboardView(){

        if(dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/Student/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        createStage(loader);
    }

    public void showStudentWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Student/Student.fxml"));
        StudentController studentController = new StudentController();
        loader.setController(studentController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }
}
