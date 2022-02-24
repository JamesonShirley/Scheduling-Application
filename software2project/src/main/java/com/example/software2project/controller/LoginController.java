package com.example.software2project.controller;

import com.example.software2project.JDBC;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
/**
 * Class Login Controller
 */

/**
 * @author Jameson Shirley
 */
public class LoginController {
    /**
     * initializes the page and converts page to french if language is french
     */
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
    /**
     * local time zone label
     */
    @FXML
    private Label localTimeZone;
    /**
     * locale date time text field
     */
    @FXML
    private TextField localeDateTime;
    /**
     * login button to validate credentials
     */
    @FXML
    private Button login;
    /**
     * password textfield
     */
    @FXML
    private TextField password;
    /**
     * password text
     */
    @FXML
    private Label passwordText;
    /**
     * username text field
     */
    @FXML
    private TextField userID;
    /**
     * username text label
     */
    @FXML
    private Label usernameText;
    /**
     * welcome text label
     */
    @FXML
    private Text welcome;

    /**
     * validates a users credentials, logs their login attempt, displays an error message if invalid credentials, navigates if valid to appointment page
     * @param event event
     * @throws IOException if the new page fails to load
     * @throws SQLException if there is a sql error
     */
    @FXML
    void loginBtnClicked(ActionEvent event) throws IOException, SQLException {
        File p = new File("LoginController.java");
        String path = p.getAbsolutePath();
        String permPath = path.substring(0, path.length() - 37);
        permPath = permPath.concat("login_activity.txt");
        File f = new File(permPath);
        Query query = new Query();
        if (query.login(userID.getText(), password.getText())) {
            BufferedWriter bw = new BufferedWriter((new FileWriter(f, true)));
            bw.append(userID.getText() + " Success "+ ZonedDateTime.now()+"\n");
            bw.close();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm");
            ZonedDateTime curr = ZonedDateTime.now(ZoneId.systemDefault());
            ZonedDateTime curr2 = curr.plusMinutes(15).plusSeconds(1);
            boolean noAppt = true;
            String today = curr.format(formatter);
            JDBC temp = new JDBC();
            temp.makeConnection();
            temp.makePreparedStatement("SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID\n" +
                    "FROM appointments a INNER JOIN users u ON u.User_ID = a.User_ID\n" + "WHERE DATE(a.Start) = ? AND u.User_Name = ?",temp.getConnection());
            temp.getPreparedStatement().setString(1, today);
            temp.getPreparedStatement().setString(2, userID.getText());
            ResultSet results = temp.getPreparedStatement().executeQuery();
            while (results.next()){
                ZonedDateTime start = ZonedDateTime.ofInstant(results.getTimestamp("Start").toInstant(), ZoneId.of(ZoneId.systemDefault().getId()));
                if (Date.from(curr.toInstant()).before(Date.from(start.toInstant())) &&  Date.from(curr2.toInstant()).after(Date.from(start.toInstant()))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("APPOINTMENT");
                    alert.setContentText("You have an appointment at with ID of " + results.getInt("Appointment_ID") + " at " + start.format(formatter2));
                    alert.showAndWait();
                    noAppt = false;
                    break;
                }
            }
            if(noAppt) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("APPOINTMENT");
                alert.setContentText("You have no appointments");
                alert.showAndWait();
            }
            temp.closeConnection();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Main.class.getResource("appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else{
            BufferedWriter bw = new BufferedWriter((new FileWriter(f, true)));
            bw.append(userID.getText() + "|Fail|"+ ZonedDateTime.now()+"\n");
            bw.close();
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
