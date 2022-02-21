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
import javafx.stage.Stage;

import java.io.IOException;

public class CustController {
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> addCol;

    @FXML
    private TableColumn<?, ?> codeCol;

    @FXML
    private TableColumn<?, ?> countryCol;

    @FXML
    private TableView<?> custTable;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private TableColumn<?, ?> stateCol;

    @FXML
    void onAddBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("addCust.fxml"));
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
    void onDeleteBtnClicked(ActionEvent event) {

    }

    @FXML
    void onModifyBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("modifyCust.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onReportBtnClicked(ActionEvent event) {

    }

}
