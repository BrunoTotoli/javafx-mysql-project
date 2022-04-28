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
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private TextField textFieldEmail;

    @FXML
    private DatePicker textFieldBirthDate;

    @FXML
    private TextField textFieldBaseSalary;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorEmail;

    @FXML
    private Label labelErrorBirthDate;

    @FXML
    private Label labelErrorBaseSalary;

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
        Constraints.setTextFieldMaxLength(textFieldName, 70);
        Constraints.setTextFieldDouble(textFieldBaseSalary);
        Constraints.setTextFieldMaxLength(textFieldEmail, 70);
        Utils.formatDatePicker(textFieldBirthDate, "dd/MM/yyyy");
    }

    public void updateFormData() {
        if (entity == null) {
            throw new IllegalStateException("entity was null");
        }
        textFieldId.setText(String.valueOf(entity.getId()));
        textFieldName.setText(entity.getName());
        textFieldEmail.setText(entity.getEmail());
        Locale.setDefault(Locale.US);
        textFieldBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));
        if(entity.getBirthDate() != null){
            textFieldBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
        }


    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }
}
