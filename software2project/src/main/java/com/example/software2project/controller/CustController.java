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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
/**
 * Class Customer Controller
 */

/**
 * @author Jameson Shirley
 */
public class CustController {
    /**
     * initializes the page and sets table values
     * @throws SQLException if there is a sql error
     */
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
        if(CustList.getAllCust().size() > 0){
            custTable.getSelectionModel().select(0);
        }

    }
    Stage stage;
    Parent scene;
    /**
     * column for customer address
     */
    @FXML
    private TableColumn<Customer, String> addCol;
    /**
     * column to hold postal code
     */
    @FXML
    private TableColumn<Customer, String> codeCol;
    /**
     * column to hold country
     */
    @FXML
    private TableColumn<Customer, String> countryCol;
    /**
     * table to hold customers
     */
    @FXML
    private TableView<Customer> custTable;
    /**
     * column to hold customer name
     */
    @FXML
    private TableColumn<Customer, String> nameCol;
    /**
     * column to hold phone number
     */
    @FXML
    private TableColumn<Customer, String> phoneCol;
    /**
     * column to hold state or province
     */
    @FXML
    private TableColumn<Customer, String> stateCol;
    /**
     * column to hold customer id
     */
    @FXML
    private TableColumn<Customer, Integer> idCol;

    /**
     * navigates to page to add customer
     * @param event event
     * @throws IOException if the new page fails to load
     */
    @FXML
    void onAddBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("addCust.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * navigates to appointment page
     * @param event event
     * @throws IOException if the new page fails to load
     */
    @FXML
    void onApptBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * deletes a customer
     * @param event event
     * @throws SQLException if there is a sql error
     */
    @FXML
    void onDeleteBtnClicked(ActionEvent event) throws SQLException {
        Query query = new Query();
        String customerName = custTable.getSelectionModel().getSelectedItem().getName();
        query.deleteApptCustomer(custTable.getSelectionModel().getSelectedItem().getId());
        query.deleteCust(custTable.getSelectionModel().getSelectedItem().getId());
        CustList.deleteAllCust();
        query.customers();
        custTable.setItems(CustList.getAllCust());
        addCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        if(CustList.getAllCust().size() > 0){
            custTable.getSelectionModel().select(0);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Deleted");
        alert.setContentText(customerName + " has been successfully deleted.");
        alert.showAndWait();
    }

    /**
     * navigates to customer modification page
     * @param event event
     * @throws IOException if the new page fails to load
     * @throws SQLException if there is a sql error
     */
    @FXML
    void onModifyBtnClicked(ActionEvent event) throws IOException, SQLException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("modifyCust.fxml"));
        Scene scene = new Scene(loader.load());
        ModifyCustController MCC = loader.getController();
        MCC.sendCust(custTable.getSelectionModel().getSelectedItem());
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();


    }

    /**
     * novigates to report page
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

}
