package com.example.software2project.model;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String state;
    private String country;
    private String code;
    private String phone;
    public Customer(int id, String name, String address, String state, String country, String code, String phone){
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.country = country;
        this.code = code;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
