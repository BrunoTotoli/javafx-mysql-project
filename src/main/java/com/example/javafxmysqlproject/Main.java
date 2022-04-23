package com.example.javafxmysqlproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        ScrollPane scrollPane = loader.load();

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        Scene mainScene = new Scene(scrollPane);

        stage.setTitle("JavaFx App");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}