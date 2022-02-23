package com.example.software2project.controller;

import com.example.software2project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginReportController {
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableView<?> loginTable;

    @FXML
    private ToggleGroup report;

    @FXML
    private TableColumn<?, ?> userCol;

    @FXML
    void loginSel(ActionEvent event) {

    }

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
    void overviewSel(ActionEvent event) {

    }

    @FXML
    void scheduleSel(ActionEvent event) {

    }

}
