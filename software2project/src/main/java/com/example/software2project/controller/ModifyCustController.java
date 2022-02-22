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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ModifyCustController {
    public void initialize() throws SQLException {
        Query query = new Query();
        country.setItems((ObservableList<String>) query.getCountries());
    }
    Stage stage;
    Parent scene;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> country;

    @FXML
    private ComboBox<String> firstLvlDiv;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField postal;

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void countryChange(ActionEvent event) throws SQLException {
        Query query = new Query();
        firstLvlDiv.setItems(query.getDivisions(country.getSelectionModel().getSelectedItem()));
    }

}