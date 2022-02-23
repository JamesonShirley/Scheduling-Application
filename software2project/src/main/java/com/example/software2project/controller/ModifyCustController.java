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

public class ModifyCustController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> country;

    @FXML
    private ComboBox<String> firstLvlDiv;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField postal;

    @FXML
    void onCancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Main.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

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

    @FXML
    void countryChange(ActionEvent event) throws SQLException {
        Query query = new Query();
        firstLvlDiv.setItems(query.getDivisions(country.getSelectionModel().getSelectedItem()));
    }

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