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

    /**
     * returns all customers
     * @return customer list
     */
    public static ObservableList<Customer> getAllCust(){return custlist;}

    /**
     * adds a customer to the list
     * @param cust customer
     */
    public static void addCust(Customer cust) {custlist.add(cust);}

    /**
     * clears the customer list
     */
    public static void deleteAllCust(){
        CustList.getAllCust().clear();
    }
}
