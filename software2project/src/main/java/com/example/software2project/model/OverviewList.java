package com.example.software2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Class Overview List
 */

/**
 * @author Jameson Shirley
 */
public class OverviewList {
    private static ObservableList<Overview> overviews = FXCollections.observableArrayList();

    /**
     * returns all overviews
     * @return overview list
     */
    public static ObservableList<Overview> getAllOverviews(){return overviews;}

    /**
     * adds an overview to the list
     * @param overview overview
     */
    public static void addOverview(Overview overview) {overviews.add(overview);}

    /**
     * clears all overviews from the list
     */
    public static void deleteAllOverviews(){
        OverviewList.getAllOverviews().clear();
    }
}
