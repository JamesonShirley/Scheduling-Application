/**
 * module information
 */
module com.example.software2project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.software2project to javafx.fxml;
    exports com.example.software2project;
    exports com.example.software2project.controller;
    opens com.example.software2project.controller to javafx.fxml;
    exports com.example.software2project.model;
    opens com.example.software2project.model;
}