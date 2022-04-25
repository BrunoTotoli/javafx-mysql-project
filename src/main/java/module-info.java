module com.example.javafxmysqlproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxmysqlproject to javafx.fxml;
    exports com.example.javafxmysqlproject;
    exports com.example.javafxmysqlproject.controllers;
    opens com.example.javafxmysqlproject.controllers to javafx.fxml;
    opens com.example.javafxmysqlproject.model.entities;
    opens com.example.javafxmysqlproject.model.services;

}