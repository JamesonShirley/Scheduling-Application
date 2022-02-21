package com.example.software2project.controller;

import com.example.software2project.Main;
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

public class ModifyApptController {
    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<?> contact;

    @FXML
    private TextField custID;

    @FXML
    private TextArea description;

    @FXML
    private TextField endDate;

    @FXML
    private TextField endTime;

    @FXML
    private TextField id;

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