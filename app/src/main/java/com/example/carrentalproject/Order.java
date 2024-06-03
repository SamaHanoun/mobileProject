package com.example.carrentalproject;

import java.util.Date;

public class Order {

    private int id;
    private int userId;
    private int carId;
    private Date acquisitionDate;
    private String timeLapsed;

    public Order(int id, int userId, int carId, Date acquisitionDate, String timeLapsed) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.acquisitionDate = acquisitionDate;
        this.timeLapsed = timeLapsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getTimeLapsed() {
        return timeLapsed;
    }

    public void setTimeLapsed(String timeLapsed) {
        this.timeLapsed = timeLapsed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", acquisitionDate=" + acquisitionDate +
                ", timeLapsed='" + timeLapsed + '\'' +
                '}';
    }
}
