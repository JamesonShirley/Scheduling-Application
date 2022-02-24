package com.example.software2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OverviewList {
    private static ObservableList<Overview> overviews = FXCollections.observableArrayList();
    public static ObservableList<Overview> getAllOverviews(){return overviews;}
    public static void addOverview(Overview overview) {overviews.add(overview);}
    public static void deleteAllOverviews(){
        OverviewList.getAllOverviews().clear();
    }
}
