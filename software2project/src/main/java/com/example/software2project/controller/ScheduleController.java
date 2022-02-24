package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Appointment;
import com.example.software2project.model.ApptList;
import com.example.software2project.model.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ScheduleController {
    public void initialize() throws SQLException {
        Query query = new Query();
        user.setItems(query.getContacts());
        user.getSelectionModel().select(0);
        ApptList.deleteAllAppt();
        query.userAppt(query.getContactId(user.getSelectionModel().getSelectedItem()));
        apptTable.setItems(ApptList.getAllAppt());
        conCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        if(ApptList.getAllAppt().size() > 0){
            apptTable.getSelectionModel().select(0);
        }
    }
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton apptOverviewSel;

    @FXML
    private TableView<Appointment> apptTable;

    @FXML
    private TableColumn<?, ?> conCol;

    @FXML
    private TableColumn<?, ?> custCol;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableColumn<?, ?> endCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private RadioButton loginSel;

    @FXML
    private ToggleGroup report;

    @FXML
    private RadioButton scheduleSel;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private ComboBox<String> user;

    @FXML
    void loginSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("loginRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onApptBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onCustomerBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void overviewSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("apptOverview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void scheduleSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("scheduleRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void userChange(ActionEvent event) throws SQLException {
        Query query = new Query();
        ApptList.deleteAllAppt();
        query.userAppt(query.getContactId(user.getSelectionModel().getSelectedItem()));
        apptTable.setItems(ApptList.getAllAppt());
        conCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        if(ApptList.getAllAppt().size() > 0){
            apptTable.getSelectionModel().select(0);
        }
    }

}