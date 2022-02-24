package com.example.software2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatusList {
    private static ObservableList<Status> statuses = FXCollections.observableArrayList();
    public static ObservableList<Status> getAllStatuses(){return statuses;}
    public static void addStatus(Status status) {statuses.add(status);}
    public static void deleteAllStatuses(){ StatusList.getAllStatuses().clear();
    }
}
