package com.example.software2project.model;
/**
 * Class Customer
 */

/**
 * @author Jameson Shirley
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String state;
    private String country;
    private String code;
    private String phone;

    /**
     * creates a customer object
     * @param id id
     * @param name name
     * @param address address
     * @param state state
     * @param country country
     * @param code code
     * @param phone phone
     */
    public Customer(int id, String name, String address, String state, String country, String code, String phone){
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.country = country;
        this.code = code;
        this.phone = phone;
    }

    /**
     * returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets the address
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * returns the state
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * sets the state
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * returns the country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the country
     * @param country country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * returns the postal code
     * @return postal code
     */
    public String getCode() {
        return code;
    }

    /**
     * sets the postal code
     * @param code postal code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * returns the phone number
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets the phone number
     * @param phone phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * returns the customer id
     * @return customer id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the customer id
     * @param id customer id
     */
    public void setId(int id) {
        this.id = id;
    }
}
