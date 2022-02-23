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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ApptController {
    public void initialize() throws SQLException {
        ApptList.deleteAllAppt();
        Query query = new Query();
        query.allAppt();
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

    }
    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup apptFilter;

    @FXML
    private RadioButton all;


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
    void monthlySelect(ActionEvent event) throws SQLException {
        ApptList.deleteAllAppt();
        Query query = new Query();
        query.monthAppt();
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
    void onDeleteBtnClicked(ActionEvent event) throws SQLException {
        int id = apptTable.getSelectionModel().getSelectedItem().getId();
        String type = apptTable.getSelectionModel().getSelectedItem().getType();
        Query query = new Query();
        query.deleteApptId(apptTable.getSelectionModel().getSelectedItem().getId());
        ApptList.deleteAllAppt();
        query.allAppt();
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
        all.selectedProperty().setValue(Boolean.TRUE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Cancelled");
        alert.setContentText("Appointment " + id + " of type " + type + " has been successfully cancelled.");
        alert.showAndWait();
    }

    @FXML
    void onModifyBtnClicked(ActionEvent event) throws IOException, SQLException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("modifyAppt.fxml"));
        Scene scene = new Scene(loader.load());
        ModifyApptController MAC = loader.getController();
        MAC.sendAppt(apptTable.getSelectionModel().getSelectedItem());
        System.out.println(apptTable.getSelectionModel().getSelectedItem().getId());
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onReportBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("apptOverview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void weeklySelect(ActionEvent event) throws SQLException {
        ApptList.deleteAllAppt();
        Query query = new Query();
        query.weekAppt();
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
    }

    @FXML
    void allSelect(ActionEvent event) throws SQLException {
        ApptList.deleteAllAppt();
        Query query = new Query();
        query.allAppt();
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
    }

}