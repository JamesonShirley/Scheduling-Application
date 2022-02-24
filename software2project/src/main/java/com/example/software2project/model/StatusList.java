package com.example.software2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Class Status List
 */

/**
 * @author Jameson Shirley
 */
public class StatusList {
    private static ObservableList<Status> statuses = FXCollections.observableArrayList();

    /**
     * returns all statuses
     * @return status list
     */
    public static ObservableList<Status> getAllStatuses(){return statuses;}

    /**
     * adds a status
     * @param status status
     */
    public static void addStatus(Status status) {statuses.add(status);}

    /**
     * deletes a status
     */
    public static void deleteAllStatuses(){ StatusList.getAllStatuses().clear();
    }
}
