package com.example.software2project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
/**
 * Class Main
 */

/**
 * @author Jameson Shirley
 */
public class Main extends Application {
    /**
     * starts the application
     * @param stage stage
     * @throws IOException if the page fails to load
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * launches start
     * @param args arguments
     * @throws SQLException if there is a sql error
     */
    public static void main(String[] args) throws SQLException {
        launch();
    }
}