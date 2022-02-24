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
    public Status(String user, String time, String status){
        this.user = user;
        this.time = time;
        this.status = status;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Status.user = user;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Status.time = time;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Status.status = status;
    }
}
