package com.example.software2project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Class Customer List
 */

/**
 * @author Jameson Shirley
 */
public class CustList {
    private static ObservableList<Customer> custlist = FXCollections.observableArrayList();
    public static ObservableList<Customer> getAllCust(){return custlist;}
    public static void addCust(Customer cust) {custlist.add(cust);}
    public static boolean deleteCust(int id){
        for (Customer cust : CustList.getAllCust()){
            if (cust.getId() == id){
                return CustList.getAllCust().remove(cust);
            }
        }
        return false;
    }
    public static Customer lookupCust(int id){
        for (Customer cust : CustList.getAllCust()){
            if (cust.getId() == id){
                return cust;
            }
        }
        return null;
    }
    public static void deleteAllCust(){
        CustList.getAllCust().clear();
    }
}
