package com.example.software2project.controller;

import com.example.software2project.Main;
import com.example.software2project.model.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;

public class LoginController {
    public void initialize(){
        localeDateTime.setText(String.valueOf(ZoneId.of(ZoneId.systemDefault().getId())));
        Locale locale = Locale.getDefault();
        if(locale.getLanguage() == "fr"){
            localTimeZone.setText("Fuseau horaire local");
            login.setText("Connexion");
            passwordText.setText("Mot de passe");
            usernameText.setText("Nom d'utilisateur");
            welcome.setText("Bienvenue");
        }
    }
    Stage stage;
    Parent scene;

    @FXML
    private Label localTimeZone;

    @FXML
    private TextField localeDateTime;

    @FXML
    private Button login;

    @FXML
    private TextField password;

    @FXML
    private Label passwordText;

    @FXML
    private TextField userID;

    @FXML
    private Label usernameText;

    @FXML
    private Text welcome;

    @FXML
    void loginBtnClicked(ActionEvent event) throws IOException, SQLException {
        Query query = new Query();
        if (query.login(userID.getText(), password.getText())) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else{
            Locale locale = Locale.getDefault();
            if(locale.getLanguage() == "fr"){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Identifiant ou mot de passe incorrect");
                alert.setContentText("Veuillez entrer des informations d'identification valides");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Username or Password");
                alert.setContentText("Please enter valid credentials");
                alert.showAndWait();
            }
        }

    }

}
