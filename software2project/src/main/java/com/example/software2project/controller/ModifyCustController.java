package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Customer;
import com.example.software2project.model.Query;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
/**
 * Class Modify Customer Controller
 */

/**
 * @author Jameson Shirley
 */
public class ModifyCustController {
    Stage stage;
    Parent scene;
    /**
     * textfield for address
     */
    @FXML
    private TextField address;
    /**
     * combobox for country
     */
    @FXML
    private ComboBox<String> country;
    /**
     * combobox for state or province
     */
    @FXML
    private ComboBox<String> firstLvlDiv;
    /**
     * textfield for id
     */
    @FXML
    private TextField id;
    /**
     * textfield for customer name
     */
    @FXML
    private TextField name;
    /**
     * textfield for phone number
     */
    @FXML
    private TextField phone;
    /**
     * textfield for postal code
     */
    @FXML
    private TextField postal;

    /**
     * cancels the modify customer dialogue
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * saves the customer if all fields are valid
     * @param event event
     * @throws IOException if the page fails to load
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException {
        try {
            Query query = new Query();
            int div = query.getDivId(firstLvlDiv.getSelectionModel().getSelectedItem());
            Customer cust = new Customer(Integer.parseInt(id.getText()), name.getText(), address.getText(), "Placeholder", "Placeholder", postal.getText(), phone.getText());
            query.updateCust(cust.getId(), cust.getName(), cust.getAddress(), cust.getCode(), cust.getPhone(), div);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please enter valid inputs");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please enter valid inputs");
            alert.showAndWait();
        }


    }

    /**
     * changes the division combobox based on country
     * @param event event
     * @throws SQLException if there is a sql error
     */
    @FXML
    void countryChange(ActionEvent event) throws SQLException {
        Query query = new Query();
        firstLvlDiv.setItems(query.getDivisions(country.getSelectionModel().getSelectedItem()));
    }

    /**
     * sends a customer to the new page
     * @param cust customer
     * @throws SQLException if there is a sql error
     */
    public void sendCust(Customer cust) throws SQLException {
        Query query = new Query();
        country.setItems((ObservableList<String>) query.getCountries());
        address.setText(String.valueOf(cust.getAddress()));
        country.getSelectionModel().select(String.valueOf(cust.getCountry()));
        firstLvlDiv.setItems(query.getDivisions(country.getSelectionModel().getSelectedItem()));
        firstLvlDiv.getSelectionModel().select(cust.getState());
        id.setText((String.valueOf(cust.getId())));
        name.setText(String.valueOf(cust.getName()));
        phone.setText(String.valueOf(cust.getPhone()));
        postal.setText(String.valueOf(cust.getCode()));
    }

}