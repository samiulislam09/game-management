package com.example.gamemanagement.Views;

import com.example.gamemanagement.Controllers.Student.StudentController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    //Student views
    private final StringProperty studentSelectedMenuItem;
    private AnchorPane dashboardView;

    private AnchorPane reservationView;
    public ViewFactory(){
        this.studentSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getStudentSelectedMenuItem() {
        return studentSelectedMenuItem;
    }

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

    public AnchorPane getReservationView() {
        if(reservationView == null){
            try{
                reservationView = new FXMLLoader(getClass().getResource("/Student/ReservationList.fxml")).load();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return reservationView;
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
        stage.setTitle("Game Management");
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
}
