package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Appointment;
import com.example.software2project.model.ApptList;
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
/**
 * Class Schedule Report Controller
 */

/**
 * @author Jameson Shirley
 */
public class ScheduleController {
    /**
     * populates the schedule table and initializes the page
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        Query query = new Query();
        user.setItems(query.getContacts());
        user.getSelectionModel().select(0);
        ApptList.deleteAllAppt();
        query.userAppt(query.getContactId(user.getSelectionModel().getSelectedItem()));
        apptTable.setItems(ApptList.getAllAppt());
        conCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        if(ApptList.getAllAppt().size() > 0){
            apptTable.getSelectionModel().select(0);
        }
    }
    Stage stage;
    Parent scene;
    /**
     * radiobutton for appointment report
     */
    @FXML
    private RadioButton apptOverviewSel;
    /**
     * table to hold schedule
     */
    @FXML
    private TableView<Appointment> apptTable;
    /**
     * column to hold contact name
     */
    @FXML
    private TableColumn<?, ?> conCol;
    /**
     * column to hold customer id
     */
    @FXML
    private TableColumn<?, ?> custCol;
    /**
     * column to hold description
     */
    @FXML
    private TableColumn<?, ?> descCol;
    /**
     * column to hold end date and time
     */
    @FXML
    private TableColumn<?, ?> endCol;
    /**
     * column to hold appointment id
     */
    @FXML
    private TableColumn<?, ?> idCol;
    /**
     * radiobutton for login
     */
    @FXML
    private RadioButton loginSel;
    /**
     * toggle group for report radio buttons
     */
    @FXML
    private ToggleGroup report;
    /**
     * radio button for schedule report
     */
    @FXML
    private RadioButton scheduleSel;
    /**
     * column to hold start date and time
     */
    @FXML
    private TableColumn<?, ?> startCol;
    /**
     * column to hold appointment title
     */
    @FXML
    private TableColumn<?, ?> titleCol;
    /**
     * column to hold appointment type
     */
    @FXML
    private TableColumn<?, ?> typeCol;
    /**
     * combobox to hold user names to select report parameters
     */
    @FXML
    private ComboBox<String> user;

    /**
     * navigate to the login report
     * @param event
     * @throws IOException
     */
    @FXML
    void loginSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("loginRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the appointment page
     * @param event
     * @throws IOException
     */
    @FXML
    void onApptBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the customer page
     * @param event
     * @throws IOException
     */
    @FXML
    void onCustomerBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the overview report
     * @param event
     * @throws IOException
     */
    @FXML
    void overviewSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("apptOverview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the schedule report
     * @param event
     * @throws IOException
     */
    @FXML
    void scheduleSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("scheduleRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * changes user for the schedule
     * @param event
     * @throws SQLException
     */
    @FXML
    void userChange(ActionEvent event) throws SQLException {
        Query query = new Query();
        ApptList.deleteAllAppt();
        query.userAppt(query.getContactId(user.getSelectionModel().getSelectedItem()));
        apptTable.setItems(ApptList.getAllAppt());
        conCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        if(ApptList.getAllAppt().size() > 0){
            apptTable.getSelectionModel().select(0);
        }
    }

}