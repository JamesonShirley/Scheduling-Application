package com.example.software2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Class Appointment List
 */

/**
 * @author Jameson Shirley
 */
public class ApptList {
    private static ObservableList<Appointment> apptlist = FXCollections.observableArrayList();

    /**
     * returns all appointments
     * @return appointment list
     */
    public static ObservableList<Appointment> getAllAppt(){return apptlist;}

    /**
     * adds an appointment to the list
     * @param appt appointment
     */
    public static void addAppt(Appointment appt) {apptlist.add(appt);}


    /**
     * clears the appointment list
     */
    public static void deleteAllAppt(){
        ApptList.getAllAppt().clear();
    }
}
