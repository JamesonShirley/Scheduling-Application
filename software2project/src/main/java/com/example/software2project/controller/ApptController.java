package com.example.software2project.controller;
import com.example.software2project.JDBC;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * Class Appointment Controller
 */

/**
 * @author Jameson Shirley
 */
public class ApptController {
    /**
     * initializes the page values and sets the table values
     * @throws SQLException if there is a sql error
     */
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

    /**
     * appointment filter toggle group
     */
    @FXML
    private ToggleGroup apptFilter;

    /**
     * radiobutton to get all appointments
     */
    @FXML
    private RadioButton all;

    /**
     * table to hold all appointments
     */
    @FXML
    private TableView<Appointment> apptTable;
    /**
     * Column in table to hold contact name
     */
    @FXML
    private TableColumn<Appointment, String> conCol;
    /**
     * column to hold customer id
     */
    @FXML
    private TableColumn<Appointment, Integer> custCol;
    /**
     * column to hold description of appointment
     */
    @FXML
    private TableColumn<Appointment, String> descCol;
    /**
     * column to hold end date and time
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    /**
     * column to hold appointmnet id
     */
    @FXML
    private TableColumn<Appointment, Integer> idCol;
    /**
     * column to hold location for appointment
     */
    @FXML
    private TableColumn<Appointment, String> locCol;
    /**
     * column to hold start date and time
     */
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;

    /**
     * column to hold title of appointment
     */
    @FXML
    private TableColumn<Appointment, String> titleCol;
    /**
     * column to hold type of appointment
     */
    @FXML
    private TableColumn<Appointment, String> typeCol;
    /**
     * column to hold user id
     */
    @FXML
    private TableColumn<Appointment, Integer> userCol;

    /**
     * selects appointments for current month
     * @param event event
     * @throws SQLException if there is a sql error
     */
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

    /**
     * Navigates to page to add an appointment
     * @param event event
     * @throws IOException if the new page fails to load
     */
    @FXML
    void onAddBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("addAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to customer page
     * @param event event
     * @throws IOException if the new page fails to load
     */
    @FXML
    void onCustomerBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * deletes an appointment
     * @param event event
     * @throws SQLException if there is a sql error
     */
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

    /**
     * navigates to page to modify an appointment
     * @param event event
     * @throws IOException if the new page fails to load
     * @throws SQLException if there is a sql error
     */
    @FXML
    void onModifyBtnClicked(ActionEvent event) throws IOException, SQLException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("modifyAppt.fxml"));
        Scene scene = new Scene(loader.load());
        ModifyApptController MAC = loader.getController();
        MAC.sendAppt(apptTable.getSelectionModel().getSelectedItem());
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * navigates to report page
     * @param event event
     * @throws IOException if the new page fails to load
     */
    @FXML
    void onReportBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("apptOverview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * filters appointments to given week
     * @param event event
     * @throws SQLException if there is a sql error
     */
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

    /**
     * filters appointments to all appointments
     * @param event event
     * @throws SQLException if there is a sql error
     */
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