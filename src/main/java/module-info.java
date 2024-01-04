module com.example.gamemanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.gamemanagement to javafx.fxml;
    exports com.example.gamemanagement;
    exports com.example.gamemanagement.Controllers;
    exports com.example.gamemanagement.Controllers.Admin;
    exports com.example.gamemanagement.Controllers.Student;
    exports com.example.gamemanagement.Models;
    exports com.example.gamemanagement.Views;
}