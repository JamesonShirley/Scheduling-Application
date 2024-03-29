package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Overview;
import com.example.software2project.model.OverviewList;
import com.example.software2project.model.Query;
import javafx.collections.ObservableList;
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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class Overview Report Controller
 */

/**
 * @author Jameson Shirley
 */
public class OverviewController {
    /**
     * initializes the page and populates the overview table
     * @throws SQLException if there is a sql error
     */
    public void initialize() throws SQLException {
        Query query = new Query();
        OverviewList.deleteAllOverviews();
        query.overview();
//        ObservableList<Overview> test = OverviewList.getAllOverviews();
//        test.forEach(overview -> System.out.println(overview.getMonth() + " " + overview.getType() + " " + overview.getCount()));
        overviewTable.setItems(OverviewList.getAllOverviews());
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        ZonedDateTime now = ZonedDateTime.now();
        dateTimeCreated.setText(now.format(formatter));
    }
    Stage stage;
    Parent scene;

    @FXML
    private TextField dateTimeCreated;
    /**
     * column for count of appointments
     */
    @FXML
    private TableColumn<?, ?> countCol;
    /**
     * column for month
     */
    @FXML
    private TableColumn<?, ?> monthCol;
    /**
     * table to store report info
     */
    @FXML
    private TableView<Overview> overviewTable;
    /**
     * report radio buttons
     */
    @FXML
    private ToggleGroup report;
    /**
     * column to store appointment type
     */
    @FXML
    private TableColumn<?, ?> typeCol;

    /**
     * navigates to the appointment overview report
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void apptOverviewSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("apptOverview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the login report
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void loginSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("loginRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the customer page
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void onCustomerBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the appointment page
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void onApptBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * navigates to the schedule report
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void scheduleSel(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("scheduleRep.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
