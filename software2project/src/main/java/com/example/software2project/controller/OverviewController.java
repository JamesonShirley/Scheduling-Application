package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Overview;
import com.example.software2project.model.OverviewList;
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

public class OverviewController {
    public void initialize() throws SQLException {
        Query query = new Query();
        OverviewList.deleteAllOverviews();
        query.overview();
        overviewTable.setItems(OverviewList.getAllOverviews());
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> countCol;

    @FXML
    private TableColumn<?, ?> monthCol;

    @FXML
    private TableView<Overview> overviewTable;

    @FXML
    private ToggleGroup report;

    @FXML
    private TableColumn<?, ?> typeCol;

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
    void scheduleSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("scheduleRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
