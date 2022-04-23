module com.example.javafxmysqlproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxmysqlproject to javafx.fxml;
    exports com.example.javafxmysqlproject;
}