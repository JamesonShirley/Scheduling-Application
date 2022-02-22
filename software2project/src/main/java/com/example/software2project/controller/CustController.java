package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.CustList;
import com.example.software2project.model.Customer;
import com.example.software2project.model.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CustController {
    public void initialize() throws SQLException {
        CustList.deleteAllCust();
        Query query = new Query();
        query.customers();
        custTable.setItems(CustList.getAllCust());
        addCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    }
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Customer, String> addCol;

    @FXML
    private TableColumn<Customer, String> codeCol;

    @FXML
    private TableColumn<Customer, String> countryCol;

    @FXML
    private TableView<Customer> custTable;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> stateCol;

    @FXML
    private TableColumn<Customer, Integer> idCol;

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
