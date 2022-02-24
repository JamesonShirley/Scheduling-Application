package com.example.software2project.model;
/**
 * Class Overview
 */

/**
 * @author Jameson Shirley
 */
public class Overview {
    private static String month;
    private static String type;
    private static int count;

    /**
     * creates an overview
     * @param month month
     * @param type type
     * @param count count
     */
    public Overview(String month, String type, int count){
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /**
     * returns the month
     * @return month
     */
    public static String getMonth() {
        return month;
    }

    /**
     * sets the month
     * @param month month
     */
    public static void setMonth(String month) {
        Overview.month = month;
    }

    /**
     * returns the type
     * @return type
     */
    public static String getType() {
        return type;
    }

    /**
     * sets the type
     * @param type type
     */
    public static void setType(String type) {
        Overview.type = type;
    }

    /**
     * returns the count
     * @return count
     */
    public static int getCount() {
        return count;
    }

    /**
     * sets the count
     * @param count count
     */
    public static void setCount(int count) {
        Overview.count = count;
    }
}
