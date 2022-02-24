package com.example.software2project.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
/**
 * Class Appointment
 */

/**
 * @author Jameson Shirley
 */
public class Appointment {
    private int id;
    private String title;
    private String description;
    private String loc;
    private String contact;
    private String type;
    private String start;
    private String end;
    private int custId;
    private int userId;

    /**
     * Constructor to create an appointment
     * @param id
     * @param title
     * @param description
     * @param loc
     * @param contact
     * @param type
     * @param start
     * @param end
     * @param custId
     * @param userId
     */
    public Appointment(int id, String title, String description, String loc, String contact, String type, String start, String end, int custId, int userId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.loc = loc;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
    }

    /**
     * returns the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns the title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the location
     * @return
     */
    public String getLoc() {
        return loc;
    }

    /**
     * sets the location
     * @param loc
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * returns the contact
     * @return
     */
    public String getContact() {
        return contact;
    }

    /**
     * sets the contact
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * returns the type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * sets the type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * returns the customer id
     * @return
     */
    public int getCustId() {
        return custId;
    }

    /**
     * sets the customer id
     * @param custId
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * returns the user ID
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * sets the user ID
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * returns the start date and time
     * @return
     */
    public String getStart() {
        return start;
    }

    /**
     * sets the start date and time
     * @param start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * returns the end date and time
     * @return
     */
    public String getEnd() {
        return end;
    }

    /**
     * sets the end date and time
     * @param end
     */
    public void setEnd(String end) {
        this.end = end;
    }
}
