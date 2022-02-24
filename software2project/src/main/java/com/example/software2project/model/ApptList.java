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
    public static ObservableList<Appointment> getAllAppt(){return apptlist;}
    public static void addAppt(Appointment appt) {apptlist.add(appt);}
    public static boolean deleteAppt(int id){
        for (Appointment appt : ApptList.getAllAppt()){
            if (appt.getId() == id){
                return ApptList.getAllAppt().remove(appt);
            }
        }
        return false;
    }
    public static Appointment lookupAppt(int id){
        for (Appointment appt : ApptList.getAllAppt()){
            if (appt.getId() == id){
                return appt;
            }
        }
        return null;
    }
    public static void deleteAllAppt(){
        ApptList.getAllAppt().clear();
    }
}
