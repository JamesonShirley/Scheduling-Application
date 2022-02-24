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
     * @param id appointment id
     * @param title title
     * @param description description
     * @param loc location
     * @param contact contact
     * @param type type
     * @param start start date and time
     * @param end end date and time
     * @param custId customer id
     * @param userId user id
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
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the location
     * @return location
     */
    public String getLoc() {
        return loc;
    }

    /**
     * sets the location
     * @param loc location
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * returns the contact
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * sets the contact
     * @param contact contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * returns the type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * sets the type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * returns the customer id
     * @return customer id
     */
    public int getCustId() {
        return custId;
    }

    /**
     * sets the customer id
     * @param custId customer id
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * returns the user ID
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * sets the user ID
     * @param userId user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * returns the start date and time
     * @return start date and time
     */
    public String getStart() {
        return start;
    }

    /**
     * sets the start date and time
     * @param start start date and time
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * returns the end date and time
     * @return end date and time
     */
    public String getEnd() {
        return end;
    }

    /**
     * sets the end date and time
     * @param end end date and time
     */
    public void setEnd(String end) {
        this.end = end;
    }
}
