package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleController {

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox user;

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
    private ToggleGroup report;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    void onCustomerBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
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
    void userChange(ActionEvent event) {

    }

    @FXML
    void apptOverviewSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("apptOverview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void loginSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("loginRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void scheduleSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
