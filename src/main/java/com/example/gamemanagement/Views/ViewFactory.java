package com.example.gamemanagement.Views;

import com.example.gamemanagement.Controllers.Admin.AdminController;
import com.example.gamemanagement.Controllers.Student.StudentController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AccountType loginAccountType;
    //Student views
    private final ObjectProperty<StudentMenuOptions> studentSelectedMenuItem;
    private AnchorPane dashboardView;

    private AnchorPane reservationView;
    private AnchorPane reserveView;

    /*
    Admin views
     */
    private AnchorPane addNewGameView;
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    public ViewFactory(){
        this.loginAccountType = AccountType.STUDENT;
        this.studentSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
    * Student controls
    */
    public ObjectProperty<StudentMenuOptions> getStudentSelectedMenuItem() {
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

    public AnchorPane getReserveView() {
        if(reserveView == null){
            try{
                reserveView = new FXMLLoader(getClass().getResource("/Student/Reserve.fxml")).load();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return reserveView;
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

    /*
    Admin controls
     */

    public AnchorPane getAddNewGameView() {
        if(addNewGameView == null){
            try{
                addNewGameView = new FXMLLoader(getClass().getResource("/Admin/AddNewGame.fxml")).load();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        return addNewGameView;
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);

    }
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        createStage(loader);
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
