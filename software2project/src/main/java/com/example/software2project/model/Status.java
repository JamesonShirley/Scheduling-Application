package com.example.software2project.model;

import java.time.ZonedDateTime;
/**
 * Class Status
 */

/**
 * @author Jameson Shirley
 */
public class Status {
    private static String user;
    private static String time;
    private static String status;

    /**
     * creates a status
     * @param user user
     * @param time date and time
     * @param status status
     */
    public Status(String user, String time, String status){
        this.user = user;
        this.time = time;
        this.status = status;
    }

    /**
     * returns the user
     * @return user
     */
    public static String getUser() {
        return user;
    }

    /**
     * sets the user
     * @param user user
     */
    public static void setUser(String user) {
        Status.user = user;
    }

    /**
     * returns the date and time
     * @return date and time
     */
    public static String getTime() {
        return time;
    }

    /**
     * sets the date and time
     * @param time date and time
     */
    public static void setTime(String time) {
        Status.time = time;
    }

    /**
     * returns the status
     * @return status
     */
    public static String getStatus() {
        return status;
    }

    /**
     * sets the status
     * @param status status
     */
    public static void setStatus(String status) {
        Status.status = status;
    }
}
