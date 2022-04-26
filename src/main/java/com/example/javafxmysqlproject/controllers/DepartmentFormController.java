package com.example.javafxmysqlproject.controllers;

import com.example.javafxmysqlproject.db.DBException;
import com.example.javafxmysqlproject.gui.listeners.DataChangeListener;
import com.example.javafxmysqlproject.gui.util.Alerts;
import com.example.javafxmysqlproject.gui.util.Constraints;
import com.example.javafxmysqlproject.gui.util.Utils;
import com.example.javafxmysqlproject.model.entities.Department;
import com.example.javafxmysqlproject.model.services.DepartmentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {


    private Department entity;
    private DepartmentService service;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    public void setEntity(Department entity) {
        this.entity = entity;
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

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
    private void onButtonSave(ActionEvent event) {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        } catch (DBException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);

        }
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private Department getFormData() {
        Department obj = new Department(Utils.tryParseToInt(textFieldId.getText()), textFieldName.getText());
        return obj;
    }

    @FXML
    private void onButtonCancel(ActionEvent event) {
        Utils.currentStage(event).close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(textFieldId);
        Constraints.setTextFieldMaxLength(textFieldName, 30);
    }

    public void updateFormData() {
        if (entity == null) {
            throw new IllegalStateException("entity was null");
        }
        textFieldId.setText(String.valueOf(entity.getId()));
        textFieldName.setText(entity.getName());
    }
}
