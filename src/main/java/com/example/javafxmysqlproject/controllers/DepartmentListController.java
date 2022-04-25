package com.example.javafxmysqlproject.controllers;

import com.example.javafxmysqlproject.Main;
import com.example.javafxmysqlproject.model.entities.Department;
import com.example.javafxmysqlproject.model.services.DepartmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentListController implements Initializable {

    private DepartmentService service;

    @FXML
    private Button newButton;

    private ObservableList<Department> obsList;

    @FXML
    private void onNewButtonAction() {
        System.out.println("New Action");
    }

    @FXML
    private TableView<Department> tableViewDepartment;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Department> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(obsList);
    }

}
