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
 * Class Add Customer Controller
 */

/**
 * @author Jameson Shirley
 */
public class AddCustController {
    /**
     * initializes the page
     * @throws SQLException if there is a sql error
     */
    public void initialize() throws SQLException {
        Query query = new Query();
        country.setItems((ObservableList<String>) query.getCountries());
    }
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
     * textfield for name
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
     * cancels the add customer dialogue
     * @param event event
     * @throws IOException if the new page fails to laod
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * saves the customer if values are valid or gives error if not.
     * @param event event
     * @throws IOException if the new page fails to load
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) throws IOException {
        try {
            Query query = new Query();
            int div = query.getDivId(firstLvlDiv.getSelectionModel().getSelectedItem());
            Customer cust = new Customer(1, name.getText(), address.getText(), "Placeholder", "Placeholder", postal.getText(), phone.getText());
            query.addCust(cust.getName(), cust.getAddress(), cust.getCode(), cust.getPhone(), div);
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
     * changes states and provinces if country is changed
     * @param event event
     * @throws SQLException if there is a sql error
     */
    @FXML
    void countryChange(ActionEvent event) throws SQLException {
        Query query = new Query();
        firstLvlDiv.setItems(query.getDivisions(country.getSelectionModel().getSelectedItem()));
    }

}