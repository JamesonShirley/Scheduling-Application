package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Status;
import com.example.software2project.model.StatusList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class Login Report Controller
 */

/**
 * @author Jameson Shirley
 */
public class LoginReportController {
    /**
     * initializes the page and sets table values
     * @throws IOException if the file fails to be loaded
     */
    public void initialize() throws IOException {
        StatusList.deleteAllStatuses();
        File p = new File("LoginReportController.java");
        String path = p.getAbsolutePath();
        String permPath = path.substring(0, path.length() - 43);
        permPath = permPath.concat("login_activity.txt");
        File f = new File(permPath);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String st;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        while((st = br.readLine()) != null){
            String[] myStr = st.split(" ", 3);
            String time = ZonedDateTime.parse(myStr[2]).format(formatter);
            StatusList.addStatus(new Status(myStr[0], time, myStr[1]));
        }
        loginTable.setItems(StatusList.getAllStatuses());
        dateCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));

    }
    Stage stage;
    Parent scene;
    /**
     * column to hold date and time
     */
    @FXML
    private TableColumn<?, ?> dateCol;
    /**
     * column to hold status
     */
    @FXML
    private TableColumn<?, ?> status;
    /**
     * table to hold login attempts
     */
    @FXML
    private TableView<Status> loginTable;
    /**
     * radio buttons to choose report page
     */
    @FXML
    private ToggleGroup report;
    /**
     * column to hold username
     */
    @FXML
    private TableColumn<?, ?> userCol;

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
     * @throws IOException if th epage fails to load
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
     * navigates to the overview report
     * @param event event
     * @throws IOException if the page fails to load
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
