package com.example.javafxmysqlproject.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewControlller implements Initializable {


    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction(){
        System.out.println("Inside Seller Action");
    }
    @FXML
    public void onMenuItemDepartmentAction(){
        System.out.println("Inside Department Action");
    }
    @FXML
    public void onMenuItemAboutAction(){
        System.out.println("Inside About Action");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}