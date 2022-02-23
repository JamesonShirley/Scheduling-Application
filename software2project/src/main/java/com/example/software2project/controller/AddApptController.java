package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Query;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddApptController {
    public void initialize() throws SQLException {
        Query query = new Query();
        contact.setItems(query.getContacts());
    }
    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<String> contact;

    @FXML
    private TextField custID;

    @FXML
    private TextArea description;

    @FXML
    private TextField endDate;

    @FXML
    private TextField endTime;

    @FXML
    private TextField loc;

    @FXML
    private TextField startDate;

    @FXML
    private TextField startTime;

    @FXML
    private TextField title;

    @FXML
    private TextField type;

    @FXML
    private TextField userID;

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}