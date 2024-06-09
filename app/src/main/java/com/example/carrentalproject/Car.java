package com.example.carrentalproject;

import java.util.Date;

public class Car {

    private int id;
    private String type;
    private String manufacturer;
    private String model;
    private Date prodYear;
    private String license;
    private double price;
    private boolean booked;
    private String carCondition;
    private int mileage;

    // Constructors
    public Car() {
    }

    public Car(int id, String type, String manufacturer, String model, Date prodYear, String license,
               double price, boolean booked, String carCondition, int mileage) {
        this.id = id;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.prodYear = prodYear;
        this.license = license;
        this.price = price;
        this.booked = booked;
        this.carCondition = carCondition;
        this.mileage = mileage;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getProdYear() {
        return prodYear;
    }

    public void setProdYear(Date prodYear) {
        this.prodYear = prodYear;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    // toString method to represent Car object as a string
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", prodYear=" + prodYear +
                ", license='" + license + '\'' +
                ", price=" + price +
                ", booked=" + booked +
                ", carCondition='" + carCondition + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
