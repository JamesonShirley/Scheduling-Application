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

public class ApptController {
    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup apptFilter;

    @FXML
    private TableView<?> apptTable;

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
    private TableColumn<?, ?> locCol;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> userCol;

    @FXML
    void monthlySelect(ActionEvent event) {

    }

    @FXML
    void onAddBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("addAppt.fxml"));
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
    void onDeleteBtnClicked(ActionEvent event) {

    }

    @FXML
    void onModifyBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("modifyAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onReportBtnClicked(ActionEvent event) {

    }

    @FXML
    void weeklySelect(ActionEvent event) {

    }

}