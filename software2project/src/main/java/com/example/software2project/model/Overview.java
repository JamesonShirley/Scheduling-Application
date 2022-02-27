package com.example.software2project.model;
/**
 * Class Overview
 */

/**
 * @author Jameson Shirley
 */
public class Overview {
    private  String month;
    private  String type;
    private  int count;

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
    public  String getMonth() {
        return month;
    }

    /**
     * sets the month
     * @param month month
     */
    public void setMonth(String month) {this.month = month;
    }

    /**
     * returns the type
     * @return type
     */
    public  String getType() {
        return type;
    }

    /**
     * sets the type
     * @param type type
     */
    public void setType(String type) {this.type = type;
    }

    /**
     * returns the count
     * @return count
     */
    public  int getCount() {
        return count;
    }

    /**
     * sets the count
     * @param count count
     */
    public  void setCount(int count) {
        this.count = count;
    }
}
