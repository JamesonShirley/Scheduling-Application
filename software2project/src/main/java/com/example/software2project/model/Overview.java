package com.example.software2project.model;

public class Overview {
    private static String month;
    private static String type;
    private static int count;
    public Overview(String month, String type, int count){
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public static String getMonth() {
        return month;
    }

    public static void setMonth(String month) {
        Overview.month = month;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Overview.type = type;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Overview.count = count;
    }
}
