package com.example.javafxmysqlproject.controllers;

import com.example.javafxmysqlproject.db.DBException;
import com.example.javafxmysqlproject.gui.listeners.DataChangeListener;
import com.example.javafxmysqlproject.gui.util.Alerts;
import com.example.javafxmysqlproject.gui.util.Constraints;
import com.example.javafxmysqlproject.gui.util.Utils;
import com.example.javafxmysqlproject.model.entities.Seller;
import com.example.javafxmysqlproject.model.exceptions.ValidationException;
import com.example.javafxmysqlproject.model.services.SellerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class SellerFormController implements Initializable {


    private Seller entity;
    private SellerService service;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    public void setEntity(Seller entity) {
        this.entity = entity;
    }

    public void setService(SellerService service) {
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
        } catch (ValidationException e) {
            setErrorMessages(e.getErros());
        } catch (DBException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);

        }
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private Seller getFormData() {
        ValidationException exception = new ValidationException("Validation error");
        if (textFieldName.getText() == null || textFieldName.getText().trim().equals("")) {
            exception.addError("name", "Field cant be empty");
        }
        Seller obj = new Seller();
        if (exception.getErros().size() > 0) {
            throw exception;

        }
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

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelError.setText(errors.get("name"));
        }
    }
}
