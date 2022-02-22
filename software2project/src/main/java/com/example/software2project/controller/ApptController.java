package com.example.software2project.controller;
import com.example.software2project.Main;
import com.example.software2project.model.Appointment;
import com.example.software2project.model.ApptList;
import com.example.software2project.model.CustList;
import com.example.software2project.model.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ApptController {
    public void initialize() throws SQLException {
        ApptList.deleteAllAppt();
        Query query = new Query();
        query.apptMonth();
        apptTable.setItems(ApptList.getAllAppt());
        conCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        if(ApptList.getAllAppt().size() > 0){
            apptTable.getSelectionModel().select(0);
        }
        System.out.println(query.getDivisions()[0]);

    }
    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup apptFilter;

    @FXML
    private TableView<Appointment> apptTable;

    @FXML
    private TableColumn<Appointment, String> conCol;

    @FXML
    private TableColumn<Appointment, Integer> custCol;

    @FXML
    private TableColumn<Appointment, String> descCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;


    @FXML
    private TableColumn<Appointment, Integer> idCol;

    @FXML
    private TableColumn<Appointment, String> locCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;


    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private TableColumn<Appointment, Integer> userCol;

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