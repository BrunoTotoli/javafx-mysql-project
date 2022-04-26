package com.example.javafxmysqlproject.gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

    public static Stage currentStage(ActionEvent actionEvent){
        return (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
    }

}
