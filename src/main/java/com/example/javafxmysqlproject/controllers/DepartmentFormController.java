package com.example.javafxmysqlproject.controllers;

import com.example.javafxmysqlproject.gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldName;

    @FXML
    private Label labelError;
    
    @FXML
    private Button buttonSave;
    
    @FXML
    private Button buttonCancel;
    
    @FXML
    private void onButtonSave(){
        System.out.println("onButtonSave");
    }
    
    @FXML
    private void onButtonCancel(){
        System.out.println("onButtonCancel");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(textFieldId);
        Constraints.setTextFieldMaxLength(textFieldName,30);
    }
}
